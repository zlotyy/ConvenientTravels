package com.mvc.controller;

import com.mvc.model.UserModel;
import com.mvc.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.Principal;
import java.util.Calendar;


@SessionAttributes(types = UserModel.class)     //potrzebne zeby przesylac obiekty miedzy kontrolerami gdy jest sesja
@Controller("userController")
@RequestMapping("/user")
public class UserController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    IUserService userService;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * kontroler przenosi do widoku rejestracji konta
     */
    @RequestMapping(value="/register", method = RequestMethod.GET)
    public String return_register_index(Model model){
        UserModel user = new UserModel();
        model.addAttribute("user", user);

        log.info("return_register_index");

        return "register/index";
    }

    /**
     * kontroler wywoluje serwis zapisujacy uzytkownika do bazy
     */
    @RequestMapping(value="/register", method = RequestMethod.POST)
    public String createUser(@ModelAttribute("user") @Valid UserModel user, BindingResult result){
        if(result.hasErrors()){
            log.info("Rejestracja konta - wprowadzono niepoprawne dane, zwroc formularz");
            return "register/index";
        } else {
            log.info("Rejestracja konta - dane poprawne, wywolaj serwis zapisujacy do bazy");

            Calendar timeNow = Calendar.getInstance();

            boolean queryResult;
            queryResult = userService.createUser(
                    user.getLogin(),
                    bCryptPasswordEncoder.encode(user.getPassword()),
                    user.getMail(),
                    user.getPhone(),
                    user.getName(),
                    user.getLastname(),
                    user.getMale(),
                    user.getBirthDate(),
                    timeNow
            );

            if(queryResult){
                log.info("Rejestracja konta - uzytkownik zapisany do bazy");
                return "redirect:/?registerSuccess";
            } else {
                log.info("Rejestracja konta - nie udalo sie zapisac uzytkownika do bazy");
                return "register/index";
            }
        }
    }


    @RequestMapping(value = "/account", method = RequestMethod.GET)
    public String return_account_index(Model model, HttpSession session){

        UserModel user = (UserModel)session.getAttribute("userFromSession");
        log.info("return_account_index user = " + user);

        model.addAttribute("user", user);

        return "account/index";
    }

    @RequestMapping(value = "/account/edit", method = RequestMethod.POST)
    public String editUser(@ModelAttribute("user") @Valid UserModel user, BindingResult result){
        if(result.hasErrors()){
            log.info("Edycja konta - wprowadzono niepoprawne dane, zwroc formularz");

            return "account/index";
        } else {
            log.info("Edycja konta - dane poprawne, wywolaj serwis zapisujacy do bazy");

            boolean queryResult = userService.editUser(user);

            if(queryResult){
                log.info("Edycja konta - uzytkownik zapisany do bazy");
                return "redirect:/user/account";
            } else {
                log.info("Edycja konta - nie udalo sie zapisac uzytkownika do bazy");
                return "redirect:/user/account";
            }
        }
    }
}
