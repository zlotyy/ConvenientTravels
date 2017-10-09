package com.mvc.controller;

import com.mvc.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller("homeController")
@RequestMapping("/")
public class HomeController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    IUserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public String index(){

        log.info("returnStartPage");

        return "home/index";
    }
}
