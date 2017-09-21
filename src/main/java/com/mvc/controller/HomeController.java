package com.mvc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

@Controller("homeController")
@RequestMapping("/")
public class HomeController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping(method = RequestMethod.GET)
    public String index(Principal principal){

        if(principal != null) {
            String login;
            login = principal.getName();
            log.info("Uzytkownik " + login + " zostal zalogowany");
        }

        log.info("returnStartPage");

        return "home/index";
    }
}
