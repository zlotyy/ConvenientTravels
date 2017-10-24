package com.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;


@Controller("modalController")
@RequestMapping("/modal")
public class ModalController {

    @RequestMapping("/alert/dbError")
    public String showError(Model model, @SessionAttribute("dbMessage") String dbMessage){
        model.addAttribute("alertTitle", "Błąd!");
        model.addAttribute("alertContent", dbMessage);

        return "modals/alert";
    }
}
