package com.mvc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("homeController")
public class HomeController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping("/")
    public String returnStartPage(){
        //org.apache.log4j.BasicConfigurator.configure();   //domyslna konfiguracja log4j





        log.info("returnStartPage");

        return "home/index";
    }
}
