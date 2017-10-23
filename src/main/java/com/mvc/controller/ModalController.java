package com.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.ArrayList;

@Controller("modalController")
@RequestMapping("/modal")
public class ModalController {

    @RequestMapping("/alert/dbError")
    public String showError(Model model, @SessionAttribute("dbError") ArrayList<String> dbError){
        model.addAttribute("alertTitle", "Błąd!");
        model.addAttribute("alertContent", dbError);

        return "modals/alert";
    }
}
