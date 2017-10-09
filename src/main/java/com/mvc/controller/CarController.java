package com.mvc.controller;

import com.mvc.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller("carController")
@RequestMapping("/car")
public class CarController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    IUserService userService;

    /**
     * kontroler wyswietla modal z wyborem samochodu
     */
    @RequestMapping(method = RequestMethod.GET)
    public String showModal(Model model){
        model.addAttribute("dialogTitle", "Wybierz samochód");
        model.addAttribute("dialogFormAction", "/car");

        return "modals/car";
    }
}
