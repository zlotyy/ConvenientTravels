package com.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/footer")
public class FooterController {

    @RequestMapping
    public String index(){

        return "shared/footer";
    }
}
