package com.mvc.controller;

import com.mvc.dto.DriveDTO;
import com.mvc.helpers.DateFormatHelper;
import com.mvc.helpers.ServiceResult;
import com.mvc.model.*;
import com.mvc.service.IDriveService;
import com.mvc.service.IUserService;
import org.hibernate.Hibernate;
import org.hibernate.proxy.HibernateProxy;
import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@SessionAttributes(types = {UserModel.class})
@Controller("driveController")
@RequestMapping("/drives")
public class DriveController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    IUserService userService;

    @Autowired
    IDriveService driveService;


    /**
     * kontroler wyswietla formatke dodawania przejazdu
     */
    @RequestMapping(value = "/addNewDrive", method = RequestMethod.GET)
    public String return_addNewDrive_index(Model model){
        DriveDTO driveDTO = new DriveDTO();

        model.addAttribute("driveDTO", driveDTO);

        log.info("return_addNewDrive_index");

        return "drives/addNewDrive/index";
    }

    @RequestMapping(value = "/addNewDrive", method = RequestMethod.POST)
    public String addNewDrive(@ModelAttribute("driveDTO") @Valid DriveDTO driveDTO, BindingResult bindingResult,
                              HttpSession session, Model model){
        if(bindingResult.hasErrors()){
            log.info("Dodawanie przejazdu - wprowadzono niepoprawne dane");
            return "drives/addNewDrive/index";
        } else {
            log.info("Dodawanie przejazdu - dane poprawne, wywolaj serwis zapisujacy do bazy");
            ServiceResult<DriveModel> result;
            boolean isSmokePermitted = false;
            boolean isRoundTrip = false;
            Calendar startDate = null;
            Calendar returnDate = null;
            UserModel insertUser = (UserModel)session.getAttribute("userFromSession");
            List<StopOverPlaceModel> stopOverPlaces = (List<StopOverPlaceModel>) session.getAttribute("stopOverPlacesToSave");

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
                    driveDTO.getBusStopStart(),
                    driveDTO.getCityArrival(),
                    driveDTO.getStreetArrival(),
                    startDate,
                    returnDate,
                    driveDTO.getPassengersQuantity(),
                    driveDTO.getCost(),
                    driveDTO.getLuggageSize(),
                    isSmokePermitted,
                    isRoundTrip,
                    driveDTO.getDriverComment(),
                    stopOverPlaces,
                    insertUser
            );

            if(result.isValid()){
                log.info("Dodawanie przejazdu - przejazd zapisany do bazy");
                return "redirect:/drives/myDrives";
            } else {
                log.info("Dodawanie przejazdu - nie udalo sie zapisac przejazdu do bazy");

                session.setAttribute("dbMessage", result.errorsToString());                                   //lista bledow
                model.addAttribute("dbError", true);

                return "drives/addNewDrive/index";
            }
        }
    }


    /**
     * kontroler wyswietla modal z miastami posrednimi
     */
    @RequestMapping(value = "/stopOverPlaces", method = RequestMethod.GET)
    public String showStopOverPlacesModal(Model model, @ModelAttribute("driveDTO") DriveModel drive){
        model.addAttribute("dialogTitle", "Miejsca pośrednie podróży");

        return "modals/stopOverPlaces";
    }

    /**
     * kontroler potwierdza miejsca posrednie dla przejazdu
     * @param placesArray lista miejsc posrednich w postaci JSON stringa
     */
    @RequestMapping(value = "/stopOverPlaces/confirm", method = RequestMethod.POST)
    @ResponseBody
    public void confirmStopOverPlaces(@RequestParam("placesList") String placesArray, HttpSession session){

        log.info("Potwierdz liste miejsc posrednich: " + placesArray);

        List<StopOverPlaceModel> stopOverPlaces = JSonPlacesToList(placesArray);

        session.setAttribute("stopOverPlacesToSave", stopOverPlaces);
    }

    /**
     * metoda zamienia JSON stringa na liste miejsc posrednich
     */
    private List<StopOverPlaceModel> JSonPlacesToList(String placesArray){
        List<StopOverPlaceModel> places = null;
        JSONArray jsonArray = new JSONArray(placesArray);

        if(jsonArray != null){
            places = new ArrayList<>();
            for(int i=0; i<jsonArray.length(); i+=2){
                StopOverPlaceModel place = new StopOverPlaceModel();
                place.setStopOverCity(jsonArray.getString(i));
                place.setStopOverStreet(jsonArray.getString(i+1));
                places.add(place);
            }
        }
        log.info("JSonPlacesToList() - Lista miejsc posrednich: " + places);

        return places;
    }


    /**
     * kontroler zaciaga miejsca posrednie przejazdu do tabelki
     */
    @RequestMapping(value = "/stopOverPlaces/getPlaces", method = RequestMethod.GET)
    @ResponseBody
    public List<StopOverPlaceModel> getStopOverPlacesForDrive(@ModelAttribute("driveDTO") DriveDTO driveDTO, @SessionAttribute("stopOverPlacesToSave") List<StopOverPlaceModel> stopOverPlaces){

        return stopOverPlaces;
    }


    @RequestMapping("/searchDrive")
    public String return_searchDrive_index(){


        return "drives/searchDrive/index";
    }

    @RequestMapping("/myDrives")
    public String return_myDrives_index(@SessionAttribute("userFromSession") UserModel user, Model model){
        List<DriveModel> allDrives = driveService.getUserDrives(user).getData();
        List<DriveModel> notDeletedDrives = new ArrayList<>();
        for(DriveModel drive : allDrives){
            if(!drive.isDeleted())
                notDeletedDrives.add(drive);
        }
        model.addAttribute("userDrives", notDeletedDrives);

        // zmiana formatu daty z Calendar na Stringa
        List<String> startDates = new ArrayList<>();
        for(DriveModel drive : notDeletedDrives){
            DateFormatHelper dateFormatHelper = new DateFormatHelper(drive.getStartDate(), "yyyy-MM-dd HH:mm");
            String startDate = dateFormatHelper.calendarToString_DateTimeFormat();
            startDates.add(startDate);
        }
        model.addAttribute("drivesStartDates", startDates);

        // obliczanie ilosci miejsc
        List<Integer> bookedPlaces = new ArrayList<>();
        List<Integer> maxPlaces = new ArrayList<>();
        for(DriveModel drive : notDeletedDrives){
            DriveDetailsModel driveDetails = driveService.getDriveDetails(drive).getData();

            bookedPlaces.add(drive.getBookings().size());
            maxPlaces.add(driveDetails.getPassengersQuantity());
        }
        model.addAttribute("drivesBookedPlaces", bookedPlaces);
        model.addAttribute("drivesMaxPlaces", maxPlaces);

        return "drives/myDrives/index";
    }

    @RequestMapping(value = "/myDrives/delete", method = RequestMethod.POST)
    @ResponseBody
    public void removeDrive(@RequestParam(value = "driveId", required = true) Long driveId){
        DriveModel drive = driveService.getDrive(driveId).getData();
        ServiceResult<DriveModel> result = driveService.setDriveDeleted(drive);

        log.info("Pomyslnie usunieto przejazd");
    }

    @RequestMapping("/myBookings")
    public String return_myBookings_index(){

        return "drives/myBookings/index";
    }
}
