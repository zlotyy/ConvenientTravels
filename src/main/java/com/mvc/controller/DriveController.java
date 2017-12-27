package com.mvc.controller;

import com.mvc.dto.DriveDTO;
import com.mvc.dto.SearchDrivesDTO;
import com.mvc.helpers.DateFormatHelper;
import com.mvc.helpers.ServiceResult;
import com.mvc.model.*;
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
import java.util.*;

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


    /**
     * kontroler wyswietla wyszukiwarke przejazdow
     * @return
     */
    @RequestMapping(value = "/searchDrive", method = RequestMethod.GET)
    public String return_searchDrive_index(Model model){

        SearchDrivesDTO searchDrivesDTO = new SearchDrivesDTO();

        model.addAttribute("searchDrivesDTO", searchDrivesDTO);

        return "drives/searchDrive/index";
    }

    /**
     * kontroler wyswietla liste przejazdow zgodnie z filtrami ustawionymi przez uzytkownika
     * @return
     */
    @RequestMapping(value = "/searchDrive", method = RequestMethod.POST)
    public String searchDrive(@ModelAttribute("searchDrivesDTO") SearchDrivesDTO searchDrivesDTO,
                              Model model, HttpSession session, @SessionAttribute("userFromSession") UserModel session_User){
        log.info("Wyszukiwanie przejazdu - wywolaj serwis szukajacy przejazdy");
        ServiceResult<List<DriveModel>> result;
        boolean isRoundTrip = false;
        Calendar startDate = null;
        Calendar returnDate = null;

        // Parsowanie daty na Calendar
        DateFormatHelper dateFormatHelper = new DateFormatHelper(searchDrivesDTO.getStartDate(), "yyyy-MM-dd HH:mm");
        if(searchDrivesDTO.getStartDate() != null){
            startDate = dateFormatHelper.stringToCalendar_DateTimeFormat();
        }
        dateFormatHelper = new DateFormatHelper(searchDrivesDTO.getReturnDate(), "yyyy-MM-dd HH:mm");
        if(searchDrivesDTO.getReturnDate() != null){
            returnDate = dateFormatHelper.stringToCalendar_DateTimeFormat();
        }

        // Parsowanie Stringa na boolean
        if(searchDrivesDTO.getIsRoundTrip() != null) {
            if (searchDrivesDTO.getIsRoundTrip().equals("true"))
                isRoundTrip = true;
        }

        result = driveService.getDrives(
                searchDrivesDTO.getStartPlace(),
                searchDrivesDTO.getArrivalPlace(),
                startDate,
                returnDate,
                isRoundTrip,
                searchDrivesDTO.getMaxCost(),
                searchDrivesDTO.getLuggageSize(),
                session_User
        );

        //todo: dokonczyc DAO z wyszukiwaniem przejazdu - odkomentowac filtry
        //todo: wyszukujemy w dokladny dzien
        //todo: max koszt - zrobic aby byl niewymagany
        //todo: wyszukujemy tez w miastach posrednich

        if(result.isValid()){
            log.info("Wyszukiwanie przejazdow - zwracam wyniki");

            List<DriveModel> drives = result.getData();

            // zmiana formatu daty z Calendar na Stringa
            List<String> startDates = new ArrayList<>();
            for(DriveModel drive : drives){
                dateFormatHelper = new DateFormatHelper(drive.getStartDate(), "yyyy-MM-dd HH:mm");
                String date = dateFormatHelper.calendarToString_DateTimeFormat();
                startDates.add(date);
            }

            // obliczanie ilosci miejsc w celu wyswietlenia ilosci wolnych miejsc
            List<String> availableSeats = new ArrayList<>();
            for(DriveModel drive : drives){
                DriveDetailsModel driveDetails = driveService.getDriveDetails(drive).getData();

                int bookedSeats = drive.getBookings().size();
                int maxSeats = driveDetails.getPassengersQuantity();

                availableSeats.add((maxSeats - bookedSeats) + "/" + maxSeats);
            }

            // pobieranie miejsc posrednich i parsowanie na Stringa
            List<String> stopOverPlaces = new ArrayList<>();
            for(DriveModel drive : drives){
                List<StopOverPlaceModel> list = drive.getStopOverPlaces();
                String tmp = "";

                for(StopOverPlaceModel place : list){
                    // dodaj enter przed kolejnym elementem
                    if(!list.get(0).equals(place)){
                        tmp += "<br>";
                    }
                    tmp += place.getStopOverCity() + ", " + place.getStopOverStreet();
                }

                stopOverPlaces.add(tmp);
            }

            model.addAttribute("stopOverPlaces", stopOverPlaces);
            model.addAttribute("availableSeats", availableSeats);
            model.addAttribute("drivesStartDates", startDates);
            model.addAttribute("filteredDrives", drives);

            return "drives/searchDrive/drivesList";
        } else {
            log.info("Wyszukiwanie przejazdow - blad podczas pobierania przejazdow z bazy");

            session.setAttribute("dbMessage", result.errorsToString());                                   //lista bledow
            return "redirect:/drives/searchDrive";
        }
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

    /**
     * kontroler wywoluje serwis usuwajacy przejazd
     */
    @RequestMapping(value = "/myDrives/delete", method = {RequestMethod.POST, RequestMethod.GET})
    public String removeDrive(@RequestParam(value = "driveId", required = true) Long driveId){
        DriveModel drive = driveService.getDrive(driveId).getData();
        ServiceResult<DriveModel> result = driveService.setDriveDeleted(drive);

        if(result.isValid()){
            log.info("Pomyslnie usunieto przejazd");
            return "redirect:/drives/myDrives";
        } else {
            log.error("Blad podczas usuwania przejazdu");
            // OBSLUZYC ALERT Z ERROREM
            return "drives/myDrives/index";
        }
    }

    /**
     * kontroler wyswietla modal z potwierdzeniem operacji
     */
    @RequestMapping(value = "/myDrives/delete/modal", method = RequestMethod.GET)
    public String showConfirmDialog(Model model){
        model.addAttribute("dialogTitle", "Usuwanie przejazdu");
        model.addAttribute("dialogContent", "Czy jesteś pewien, że chcesz usunąć przejazd?\nJeśli się na to zdecydujesz, niezwłocznie poinformuj o tym pasażerów!");
        model.addAttribute("dialogFormAction", "/drives/myDrives/delete/confirm/confirmed");
        model.addAttribute("dialogFormName", "deleteConfirmForm");

        return "modals/confirmOnGrid";
    }


    /**
     * kontroler wyswietla widok edycji przejazdu
     */
    @RequestMapping(value = "/myDrives/edit", method = RequestMethod.GET)
    public String editDrive(@RequestParam(value = "driveId", required = true) Long driveId, Model model){
        DriveModel drive = driveService.getDrive(driveId).getData();
        DriveDetailsModel driveDetails = driveService.getDriveDetails(drive).getData();
        DriveDTO driveDTO = new DriveDTO();
        String startDate = null;
        String returnDate = null;
        String isSmokePermitted = null;
        String isRoundTrip = null;

        // zamiana daty z Calendar na Stringa
        DateFormatHelper dateFormatHelper = new DateFormatHelper(drive.getStartDate(), "yyyy-MM-dd HH:mm");
        if(drive.getStartDate() != null){
            startDate = dateFormatHelper.calendarToString_DateTimeFormat();
        }
        dateFormatHelper = new DateFormatHelper(drive.getReturnDate(), "yyyy-MM-dd HH:mm");
        if(drive.getReturnDate() != null){
            returnDate = dateFormatHelper.calendarToString_DateTimeFormat();
        }
        if(driveDetails.isSmokePermitted()){
            isSmokePermitted = "true";
        }
        if(drive.isRoundTrip()){
            isRoundTrip = "true";
        }

        driveDTO.setCityStart(drive.getCityStart());
        driveDTO.setStreetStart(drive.getStreetStart());
        driveDTO.setExactPlaceStart(drive.getExactPlaceStart());
        driveDTO.setStartDate(startDate);
        driveDTO.setCityArrival(drive.getCityArrival());
        driveDTO.setStreetArrival(drive.getStreetArrival());
        driveDTO.setExactPlaceArrival(drive.getExactPlaceArrival());
        driveDTO.setPassengersQuantity(driveDetails.getPassengersQuantity());
        driveDTO.setCost(drive.getCost());
        driveDTO.setLuggageSize(driveDetails.getLuggageSize());
        driveDTO.setIsSmokePermitted(isSmokePermitted);
        driveDTO.setIsRoundTrip(isRoundTrip);
        driveDTO.setReturnDate(returnDate);
        driveDTO.setDriverComment(driveDetails.getDriverComment());

        model.addAttribute("driveDTO", driveDTO);
        model.addAttribute("driveId", driveId);

        return "drives/myDrives/editDrive";
    }

    /**
     * kontroler wywoluje serwis edytujacy dane przejazdu
     */
    @RequestMapping(value = "/myDrives/edit", method = RequestMethod.POST)
    public String editDrive(@ModelAttribute("driveDTO") @Valid DriveDTO driveDTO, BindingResult bindingResult, HttpSession session, Model model,
                           @RequestParam(value = "driveId", required = true) Long driveId){
        if(bindingResult.hasErrors()){
            log.info("Edycja przejazdu - wprowadzono niepoprawne dane, zwroc formularz");

            return "drives/myDrives/editDrive";
        } else {
            ServiceResult<DriveModel> result;
            boolean isSmokePermitted = false;
            boolean isRoundTrip = false;
            Calendar startDate = null;
            Calendar returnDate = null;
            List<StopOverPlaceModel> stopOverPlaces = null; //(List<StopOverPlaceModel>) session.getAttribute("stopOverPlacesToSave");

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

            log.info("Edycja przejazdu - dane poprawne, wywolaj serwis zapisujacy do bazy");

            result = driveService.editDrive(
                    driveDTO.getCityStart(),
                    driveDTO.getStreetStart(),
                    driveDTO.getExactPlaceStart(),
                    startDate,
                    driveDTO.getCityArrival(),
                    driveDTO.getStreetArrival(),
                    driveDTO.getExactPlaceArrival(),
                    stopOverPlaces,
                    driveDTO.getPassengersQuantity(),
                    driveDTO.getCost(),
                    driveDTO.getLuggageSize(),
                    isSmokePermitted,
                    isRoundTrip,
                    returnDate,
                    driveDTO.getDriverComment(),
                    driveId
                    );

            if(result.isValid()){
                log.info("Edycja przejazdu - przejazd zapisany do bazy");
                return "redirect:/drives/myDrives";
            } else {
                log.info("Edycja przejazdu - nie udalo sie zapisac przejazdu do bazy");

                session.setAttribute("dbMessage", result.errorsToString());                                   //lista bledow
                return "account/index";
            }
        }
    }

    @RequestMapping("/myBookings")
    public String return_myBookings_index(){

        return "drives/myBookings/index";
    }


    @RequestMapping(value = "/bookDrive", method = RequestMethod.GET)
    public String bookDrive(Model model, @RequestParam(value = "driveId", required = true) Long driveId){

        DriveModel drive = driveService.getDrive(driveId).getData();
        DriveDetailsModel driveDetails = driveService.getDriveDetails(drive).getData();
        DriveDTO driveDTO = new DriveDTO();
        String startDate = null;
        String returnDate = null;
        String isSmokePermitted = null;
        String isRoundTrip = null;

        // zamiana daty z Calendar na Stringa
        DateFormatHelper dateFormatHelper = new DateFormatHelper(drive.getStartDate(), "yyyy-MM-dd HH:mm");
        if(drive.getStartDate() != null){
            startDate = dateFormatHelper.calendarToString_DateTimeFormat();
        }
        dateFormatHelper = new DateFormatHelper(drive.getReturnDate(), "yyyy-MM-dd HH:mm");
        if(drive.getReturnDate() != null){
            returnDate = dateFormatHelper.calendarToString_DateTimeFormat();
        }
        if(driveDetails.isSmokePermitted()){
            isSmokePermitted = "true";
        }
        if(drive.isRoundTrip()){
            isRoundTrip = "true";
        }

        driveDTO.setCityStart(drive.getCityStart());
        driveDTO.setStreetStart(drive.getStreetStart());
        driveDTO.setExactPlaceStart(drive.getExactPlaceStart());
        driveDTO.setStartDate(startDate);
        driveDTO.setCityArrival(drive.getCityArrival());
        driveDTO.setStreetArrival(drive.getStreetArrival());
        driveDTO.setExactPlaceArrival(drive.getExactPlaceArrival());
        driveDTO.setPassengersQuantity(driveDetails.getPassengersQuantity());
        driveDTO.setCost(drive.getCost());
        driveDTO.setLuggageSize(driveDetails.getLuggageSize());
        driveDTO.setIsSmokePermitted(isSmokePermitted);
        driveDTO.setIsRoundTrip(isRoundTrip);
        driveDTO.setReturnDate(returnDate);
        driveDTO.setDriverComment(driveDetails.getDriverComment());

        model.addAttribute("driveDTO", driveDTO);
        model.addAttribute("driveId", driveId);

        return "drives/searchDrive/bookDrive";
    }
}
