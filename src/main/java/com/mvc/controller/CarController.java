package com.mvc.controller;

import com.mvc.model.CarModel;
import com.mvc.model.UserModel;
import com.mvc.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SessionAttributes(types = UserModel.class)     //potrzebne zeby przesylac obiekty miedzy kontrolerami gdy jest sesja
@Controller("carController")
public class CarController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    IUserService userService;

    /**
     * kontroler wyswietla modal z wyborem samochodu
     */
    @RequestMapping(value = "/car", method = RequestMethod.GET)
    public String showModal(Model model, @ModelAttribute("user") UserModel user){
        model.addAttribute("dialogTitle", "Twoje samochody");

        List<CarModel> cars = user.getCars();
        if(cars == null){
            cars = new ArrayList<>();
        }

        CarModel car = new CarModel();

        car.setCarBrand(new String());
        car.setCarModel(new String());
        car.setColor(new String());
        cars.add(car);
        user.setCars(cars);

        return "modals/car";
    }

//    @RequestMapping(value = "user/register", method = RequestMethod.GET)
//    public String showModalAgain(Model model, @ModelAttribute("user") UserModel user){
//        model.addAttribute("dialogTitle", "Twoje samochody");
//
//        log.info("Rejestracja konta - pokaz modal z samochodem ponownie");
//
//        return "register/index";
//    }


    /**
     * kontroler dodaje kolejny samochod do modala
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addNextCar(Model model, @ModelAttribute("user") UserModel user){
        model.addAttribute("dialogTitle", "Twoje samochody");

        return "modals/car";
    }
}
