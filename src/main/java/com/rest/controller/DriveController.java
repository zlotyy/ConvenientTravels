package com.rest.controller;

import com.mvc.dto.DriveDTO;
import com.mvc.helpers.DateFormatHelper;
import com.mvc.helpers.ServiceResult;
import com.mvc.model.BookingModel;
import com.mvc.model.DriveModel;
import com.mvc.model.UserModel;
import com.mvc.service.IBookingService;
import com.mvc.service.IDriveService;
import com.mvc.service.IUserService;
import com.rest.helpers.EmptyJsonResponse;
import com.rest.helpers.JsonHelper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;


//@SessionAttributes(types = {UserModel.class, ArrayList.class})     //potrzebne zeby przesylac obiekty miedzy kontrolerami gdy jest sesja
@RestController("restDriveController")
@RequestMapping("/rest")
public class DriveController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    IUserService userService;

    @Autowired
    IDriveService driveService;

    @Autowired
    IBookingService bookingService;



    @RequestMapping(value = "/addNewDrive", method = RequestMethod.POST)
    public ResponseEntity<?> addNewDrive(@RequestBody DriveDTO driveDTO, //@SessionAttribute("session_restTokensList") ArrayList<String> session_restTokens,
                                         @RequestHeader() HashMap<String, String> header){
                                                 //String userIdHeader, @RequestHeader("token") String token){

        log.info("RESTOWA METODA - DODAJ PRZEJAZD");

        // todo: naprawic to - w kontrolerze logowania tworzy sie sesja, a przy kolejnym wywolaniu atrybuty z sesji znikaja
//        if(!SessionManager.CheckIfUserLogged(header.get("token"), session_restTokens)){
//            log.info("REST - nie udalo sie utworzyc nowego przejazdu - uzytkownik nie jest zalogowany");
//            return new ResponseEntity<>(new EmptyJsonResponse(), HttpStatus.UNAUTHORIZED);
//        }

        ServiceResult<DriveModel> result;
        boolean isSmokePermitted = false;
        boolean isRoundTrip = false;
        Calendar startDate = null;
        Calendar returnDate = null;
        String header_id = header.get("userid");
        Long userId = Long.parseLong(header_id);
        UserModel insertUser = userService.getUser(userId).getData();

        // Parsowanie daty na Calendar
        DateFormatHelper dateFormatHelper = new DateFormatHelper(driveDTO.getStartDate(), "yyyy-MM-dd HH:mm");
        if(driveDTO.getStartDate() != null){
            startDate = dateFormatHelper.stringToCalendar_DateTimeFormat();
        }
        dateFormatHelper = new DateFormatHelper(driveDTO.getReturnDate(), "yyyy-MM-dd HH:mm");
        if(driveDTO.getReturnDate() != null){
            returnDate = dateFormatHelper.stringToCalendar_DateTimeFormat();
        }

        // Parsowanie Stringa na boolean
        if(driveDTO.getIsSmokePermitted() != null){
            if(driveDTO.getIsSmokePermitted().equals("true"))
                isSmokePermitted = true;
        }
        if(driveDTO.getIsRoundTrip() != null) {
            if (driveDTO.getIsRoundTrip().equals("true"))
                isRoundTrip = true;
        }

        try {
            result = driveService.addNewDrive(
                    driveDTO.getCityStart(),
                    driveDTO.getStreetStart(),
                    driveDTO.getExactPlaceStart(),
                    driveDTO.getCityArrival(),
                    driveDTO.getStreetArrival(),
                    driveDTO.getExactPlaceArrival(),
                    startDate,
                    returnDate,
                    driveDTO.getPassengersQuantity(),
                    driveDTO.getCost(),
                    driveDTO.getLuggageSize(),
                    isSmokePermitted,
                    isRoundTrip,
                    driveDTO.getDriverComment(),
                    driveDTO.getStopOverPlaces(),
                    insertUser
            );
        } catch (Exception e){
            log.info("REST - nie udalo sie utworzyc nowego przejazdu - podaj wszystkie dane");
            return new ResponseEntity<>(new EmptyJsonResponse(), HttpStatus.BAD_REQUEST);
        }

        if(result.isValid()) {
            return new ResponseEntity<>(new EmptyJsonResponse(), HttpStatus.OK);
        } else {
            log.info("REST - nie udalo sie utworzyc nowego przejazdu");
            return new ResponseEntity<>(new EmptyJsonResponse(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }



    @RequestMapping(value = "/myDrives", method = RequestMethod.GET)
    public ResponseEntity<?> getMyDrives(//@SessionAttribute("session_restTokensList") ArrayList<String> session_restTokens,
                                         @RequestHeader() HashMap<String, String> header){

        log.info("RESTOWA METODA - MOJE PRZEJAZDY");

//        if(!SessionManager.CheckIfUserLogged(header.get("token"), session_restTokens)){
//            log.info("REST - nie udalo sie utworzyc nowego przejazdu - uzytkownik nie jest zalogowany");
//            return new ResponseEntity<>(new EmptyJsonResponse(), HttpStatus.UNAUTHORIZED);
//        }

        ServiceResult<List<DriveModel>> result = new ServiceResult<>();
        String header_id = header.get("userid");
        Long userId = Long.parseLong(header_id);
        UserModel user = userService.getUser(userId).getData();

        result = driveService.getUserDrives(user);

        if(result.isValid()) {
            List<DriveModel> myDrives = result.getData();
            JSONObject drivesJSON;

            // parsowanie listy przejazdow na JSONA
            try {
                drivesJSON = JsonHelper.getJSONObjectFromList(myDrives, "myDrivesList");
            } catch (Exception e){
                return new ResponseEntity<>(new EmptyJsonResponse(), HttpStatus.INTERNAL_SERVER_ERROR);
            }

            return new ResponseEntity<>(drivesJSON.toString(), HttpStatus.OK);
        } else {
            log.info("REST - nie udalo sie pobrac przejazdow");
            return new ResponseEntity<>(new EmptyJsonResponse(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }



    @RequestMapping(value = "/myDrives/delete", method = RequestMethod.POST)
    public ResponseEntity<?> deleteDrive(@RequestBody HashMap<String, Long> driveIdMap){

        log.info("RESTOWA METODA - USUN PRZEJAZD");

        ServiceResult<DriveModel> result;
        Long driveId = driveIdMap.get("driveId");
        DriveModel drive = driveService.getDrive(driveId).getData();

        try {
            result = driveService.setDriveDeleted(drive);
        } catch (Exception e){
            log.info("REST - nie udalo sie usunac przejazdu");
            return new ResponseEntity<>(new EmptyJsonResponse(), HttpStatus.BAD_REQUEST);
        }

        if(result.isValid()) {
            return new ResponseEntity<>(new EmptyJsonResponse(), HttpStatus.OK);
        } else {
            log.info("REST - nie udalo sie usunac przejazdu");
            return new ResponseEntity<>(new EmptyJsonResponse(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }








    @RequestMapping(value = "/myBookings", method = RequestMethod.GET)
    public ResponseEntity<?> getMyBookings(@RequestHeader() HashMap<String, String> header){

        log.info("RESTOWA METODA - MOJE REZERWACJE");

        ServiceResult<List<DriveModel>> result = new ServiceResult<>();
        String header_id = header.get("userid");
        Long userId = Long.parseLong(header_id);
        UserModel user = userService.getUser(userId).getData();

        result = bookingService.getUserBookedDrives(user);

        if(result.isValid()) {
            List<DriveModel> myDrives = result.getData();
            JSONObject drivesJSON;

            // parsowanie listy przejazdow na JSONA
            try {
                drivesJSON = JsonHelper.getJSONObjectFromList(myDrives, "myBookedDrivesList");
            } catch (Exception e){
                return new ResponseEntity<>(new EmptyJsonResponse(), HttpStatus.INTERNAL_SERVER_ERROR);
            }

            return new ResponseEntity<>(drivesJSON.toString(), HttpStatus.OK);
        } else {
            log.info("REST - nie udalo sie pobrac przejazdow");
            return new ResponseEntity<>(new EmptyJsonResponse(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }





    @RequestMapping(value = "/myBookings/unbookDrive", method = RequestMethod.POST)
    public ResponseEntity<?> unbookDrive(@RequestBody HashMap<String, Long> driveIdMap,
                                         @RequestHeader() HashMap<String, String> header){

        log.info("RESTOWA METODA - ANULUJ REZERWACJE");

        ServiceResult<BookingModel> result;

        Long driveId = driveIdMap.get("driveId");
        DriveModel drive = driveService.getDrive(driveId).getData();

        String header_id = header.get("userid");
        Long userId = Long.parseLong(header_id);
        UserModel passenger = userService.getUser(userId).getData();

        BookingModel booking = bookingService.getBooking(passenger, drive).getData();

        try {
            result = bookingService.unbookDrive(passenger, booking);
        } catch (Exception e){
            log.info("REST - nie udalo sie anulowac rezerwacji");
            return new ResponseEntity<>(new EmptyJsonResponse(), HttpStatus.BAD_REQUEST);
        }

        if(result.isValid()) {
            return new ResponseEntity<>(new EmptyJsonResponse(), HttpStatus.OK);
        } else {
            log.info("REST - nie udalo sie anulowac rezerwacji");
            return new ResponseEntity<>(new EmptyJsonResponse(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
