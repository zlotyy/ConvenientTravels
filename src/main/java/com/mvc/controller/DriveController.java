package com.mvc.controller;

import com.mvc.dto.DriveDTO;
import com.mvc.model.DriveModel;
import com.mvc.model.UserModel;
import com.mvc.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Calendar;

@Controller("driveController")
@RequestMapping("/drives")
public class DriveController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    IUserService userService;

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
    public String addNewDrive(@ModelAttribute("driveDTO") @Valid DriveDTO driveDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            log.info("Dodawanie przejazdu - wprowadzono niepoprawne dane");
            return "drives/addNewDrive/index";
        } else {
            log.info("Dodawanie przejazdu - dane poprawne, wywolaj serwis zapisujacy do bazy");
            boolean isSmokePermitted;
            boolean isRoundTrip;

            if(driveDTO.getIsSmokePermitted().equals("true")){
                isSmokePermitted = true;
            }
            if(driveDTO.getIsRoundTrip().equals("true")){
                isRoundTrip = true;
            }


            return "redirect:/";
        }
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
