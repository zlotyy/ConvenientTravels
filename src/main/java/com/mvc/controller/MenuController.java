package com.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("menuController")
@RequestMapping("/menu")
public class MenuController {

    @RequestMapping()
    public String index(){

        return "shared/menu";
    }
}
