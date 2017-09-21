package com.mvc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("contactController")
@RequestMapping("/contact")
public class ContactController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping
    public String index(){

        return "contact/index";
    }
}
