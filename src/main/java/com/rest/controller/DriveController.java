package com.rest.controller;

import com.mvc.dto.DriveDTO;
import com.mvc.helpers.DateFormatHelper;
import com.mvc.helpers.ServiceResult;
import com.mvc.model.DriveModel;
import com.mvc.model.UserModel;
import com.mvc.service.IDriveService;
import com.mvc.service.IUserService;
import com.rest.helpers.EmptyJsonResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.method.P;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;

@RestController("restDriveController")
@RequestMapping("/rest")
public class DriveController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    IUserService userService;

    @Autowired
    IDriveService driveService;



    @RequestMapping(value = "/addNewDrive", method = RequestMethod.POST)
    public ResponseEntity<?> addNewDrive(@RequestBody DriveDTO driveDTO, HttpSession session,
                                         @RequestHeader("userId") HashMap<String, String> header){
                                                 //String userIdHeader, @RequestHeader("token") String token){

        log.info("RESTOWA METODA - DODAJ PRZEJAZD");

        ServiceResult<DriveModel> result;
        boolean isSmokePermitted = false;
        boolean isRoundTrip = false;
        Calendar startDate = null;
        Calendar returnDate = null;
        String s = header.get("userid");
        Long userId = Long.parseLong(s);
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

        if(result.isValid()) {
            return new ResponseEntity<>(new EmptyJsonResponse(), HttpStatus.OK);
        } else {
            log.info("REST - nie udalo sie utworzyc nowego przejazdu");
            return new ResponseEntity<>(new EmptyJsonResponse(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
