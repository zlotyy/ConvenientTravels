package com.mvc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("driveController")
@RequestMapping("/drives")
public class DriveController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping("/addNewDrive")
    public String return_addNewDrive_index(){

        return "drives/addNewDrive/index";
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
