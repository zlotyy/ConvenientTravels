package com.mvc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller("menuController")
@RequestMapping("/menu")
public class MenuController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping()
    public String index(@RequestParam(value = "registerSuccess", required = false) String registerSuccess, Model model,
                        @RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "logout", required = false) String logout){

        // Okresla czy w przypadku rejestracji konta zostalo ono utworzone
        if (registerSuccess != null) {
            model.addAttribute("registerSuccess", "Twoje konto zostało utworzone");
        }

        if (error != null) {
            model.addAttribute("logInError", "Login i hasło są nieprawidłowe!");
        }

        if (logout != null) {
            model.addAttribute("logoutSuccess", "Zostałeś pomyślnie wylogowany");
        }
        return "shared/menu";
    }
}
