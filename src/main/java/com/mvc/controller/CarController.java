package com.mvc.controller;

import com.mvc.model.CarModel;
import com.mvc.model.UserModel;
import com.mvc.service.IUserService;
import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@SessionAttributes(types = UserModel.class)     //potrzebne zeby przesylac obiekty miedzy kontrolerami gdy jest sesja
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
    public String showModal(Model model, @ModelAttribute("user") UserModel user){
        model.addAttribute("dialogTitle", "Twoje samochody");

        return "modals/car";
    }


    /**
     * kontroler zatwierdza samochody uzytkownika
     * @param carsArray lista samochodow w postaci JSON stringa z metody Ajaxowej
     */
    @RequestMapping(value = "/confirm", method = RequestMethod.POST)
    @ResponseBody
    public String confirmCars(@RequestParam("carsList") String carsArray, @ModelAttribute("user") UserModel user){

        log.info("Potwierdz liste samochodow: " + carsArray);

        List<CarModel> cars = null;
        cars = JSonCarsToList(carsArray);

        user.setCars(cars);
        log.info("user.getCars() = " + user.getCars());

        return "OK";
    }


    /**
     * metoda zamienia JSON stringa na liste samochodow
     */
    private List<CarModel> JSonCarsToList(String carsArray){
        List<CarModel> cars = null;
        JSONArray jsonArray = new JSONArray(carsArray);

        if(jsonArray != null){
            cars = new ArrayList<>();
            for(int i=0; i<jsonArray.length(); i+=3){
                CarModel car = new CarModel();
                car.setCarBrand(jsonArray.getString(i));
                car.setCarModel(jsonArray.getString(i));
                car.setColor(jsonArray.getString(i));
                cars.add(car);
            }
        }
        log.info("JSonCarsToList() - Lista samochodow: " + cars);

        return cars;
    }
}
