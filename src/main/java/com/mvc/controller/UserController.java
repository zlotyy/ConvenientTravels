package com.mvc.controller;

import com.mvc.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller("userController")
@RequestMapping("/user")
public class UserController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    IUserService userService;


    /**
     * kontroler przenosi do widoku rejestracji konta
     */
    @RequestMapping("/register")
    public String navigateToRegisterForm(Model model){
        return "register/index";
    }

    /**
     * kontroler wywoluje serwis zapisujacy uzytkownika do bazy
     */
    @RequestMapping("/register/createNewUser")
    public String createUser(Model model){
        // model.addAttribute("atrybut", "OBIEKT");

        return "login/index";
    }
}
