package com.mvc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller("adminController")
@RequestMapping("/admin")
public class AdminController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = "/admin**", method = RequestMethod.GET)
    public String index(){

        return "admin/index";
    }
}
