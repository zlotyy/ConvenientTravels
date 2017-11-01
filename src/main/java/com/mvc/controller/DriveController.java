package com.mvc.controller;

import com.mvc.dto.DriveDTO;
import com.mvc.helpers.DateFormatHelper;
import com.mvc.helpers.ServiceResult;
import com.mvc.model.CarModel;
import com.mvc.model.DriveModel;
import com.mvc.model.StopOverPlaceModel;
import com.mvc.model.UserModel;
import com.mvc.service.IDriveService;
import com.mvc.service.IUserService;
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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@SessionAttributes(types = UserModel.class)
@Controller("driveController")
@RequestMapping("/drives")
public class DriveController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    IUserService userService;

    @Autowired
    IDriveService driveService;

    @RequestMapping(value = "/addNewDrive", method = RequestMethod.GET)
    public String return_addNewDrive_index(Model model, HttpSession session){
        UserModel user = (UserModel) session.getAttribute("userFromSession");
        DriveDTO driveDTO = new DriveDTO();
        Calendar timeNow = Calendar.getInstance();

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
                    driveDTO.getStopOverPlaces(),
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
    public void confirmStopOverPlaces(@RequestParam("placesList") String placesArray, @ModelAttribute("driveDTO") DriveDTO driveDTO){

        log.info("Potwierdz liste miejsc posrednich: " + placesArray);

        List<StopOverPlaceModel> stopOverPlaces = JSonPlacesToList(placesArray);

        driveDTO.setStopOverPlaces(stopOverPlaces);
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



    @RequestMapping("/searchDrive")
    public String return_searchDrive_index(){

        return "drives/searchDrive/index";
    }

    @RequestMapping("/myDrives")
    public String return_myDrives_index(){

        return "drives/myDrives/index";
    }

    @RequestMapping("/myBookings")
    public String return_myBookings_index(){

        return "drives/myBookings/index";
    }
}
