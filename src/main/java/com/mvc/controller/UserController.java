package com.mvc.controller;

import com.mvc.dto.PasswordDTO;
import com.mvc.dto.UserRatesDTO;
import com.mvc.helpers.UserRatesCounter;
import com.mvc.model.UserModel;
import com.mvc.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
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

    /**
     * kontroler otwiera strone z profilem uzytkownika
     */
    @Transactional
    @RequestMapping(value = "/account", method = RequestMethod.GET)
    public String return_account_index(Model model, HttpSession session,
                                       @RequestParam(value = "wrongOldPassword", required = false) String wrongOldPassword){

        UserModel user = (UserModel)session.getAttribute("userFromSession");
        log.info("return_account_index user = " + user);

        //oblicz srednia ocen i wstaw do formularza
        UserRatesDTO userRatesDTO = new UserRatesDTO();
//        UserRatesCounter uRC = new UserRatesCounter();
//        userRatesDTO.setUserRates(uRC.returnAverageRate(user.getUserRates()));
//        userRatesDTO.setDrivingSkills(uRC.returnAverageRate(user.getDrivingSkills()));
        userRatesDTO.setUserRates(5);

        model.addAttribute("user", user);
        model.addAttribute("userRates", userRatesDTO);

        // Okresla czy w przypadku zmiany hasla zostalo podane poprawne poprzednie haslo
        if (wrongOldPassword != null) {
            model.addAttribute("wrongOldPassword", "Podaj poprawne poprzednie haslo");
        }

        return "account/index";
    }


    /**
     * kontroler wywoluje serwis edytujacy dane uzytkownika
     */
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


    /**
     * kontroler wywoluje serwis edytujacy haslo uzytkownika
     */
    @RequestMapping(value = "/account/changePassword", method = RequestMethod.POST)
    public String editPassword(@ModelAttribute("userPassword") @Valid PasswordDTO passwordDTO, BindingResult result, @ModelAttribute("user") @Valid UserModel user){
        if(result.hasErrors()){
            log.info("Edycja hasla - wprowadzono niepoprawne dane, zwroc formularz");

            return "account/index";
        } else {
            if(bCryptPasswordEncoder.matches(passwordDTO.getOldPassword(), user.getPassword())){
                log.info("Edycja hasla - wywolaj serwis zapisujacy do bazy");
                boolean queryResult = userService.editPassword(user, bCryptPasswordEncoder.encode(passwordDTO.getNewPassword()));

                if(queryResult){
                    log.info("Edycja hasla - uzytkownik zapisany do bazy");
                    return "redirect:/user/account";
                } else {
                    log.info("Edycja hasla - nie udalo sie zapisac uzytkownika do bazy");
                    return "redirect:/user/account";
                }
            } else {
                log.info("Edycja hasla - podane poprzednie haslo nieprawidlowe, zwroc formularz");
                return "redirect:/user/account?wrongOldPassword";
            }
        }
    }

    /**
     * kontroler wywoluje serwis usuwajacy uzytkownika
     */
    @RequestMapping(value = "/account/delete/confirm", method = RequestMethod.POST)
    public String deleteUser(@ModelAttribute("user") @Valid UserModel user, HttpServletRequest request){
        log.info("Usuwanie konta - wywolaj serwis zmieniajacy isDeleted na true");

        boolean queryResult = userService.setUserDeleted(user);

        if(queryResult){
            log.info("Usuwanie konta - uzytkownikowi ustawiono isDeleted na true");

            HttpSession session = request.getSession(false);
            SecurityContextHolder.clearContext();
            if(session != null) {
                session.invalidate();
                log.info("Wylogowano z aplikacji");
            }

            return "redirect:/?accountDeleted";
        } else {
            log.info("Usuwanie konta - nie udalo sie dla uzytkownika ustawic isDeleted na true");
            return "redirect:/user/account";
        }
    }

    /**
     * kontroler wyswietla modal z potwierdzeniem operacji
     */
    @RequestMapping(value = "/account/delete/confirm", method = RequestMethod.GET)
    public String showConfirmDialog(Model model){
        model.addAttribute("dialogTitle", "Usuwanie konta");
        model.addAttribute("dialogContent", "Czy jesteś pewien, że chcesz usunąć konto?");
        model.addAttribute("dialogFormAction", "/user/account/delete/confirm");

        return "modals/confirm";
    }

    /**
     * kontroler wyswietla modal z alertem
     */
    @RequestMapping(value = "/account/changePassword/alert", method = RequestMethod.GET)
    public String alert(Model model){
        model.addAttribute("alertTitle", "Błąd!");
        model.addAttribute("alertContent", "Podane hasła się nie zgadzają");

        return "modals/alert";
    }

    /**
     * bez tego nie da sie przekazac userPassword do JSP
     */
    @ModelAttribute("userPassword")
    public PasswordDTO getPassword(){
        return new PasswordDTO();
    }


    @ModelAttribute("userRates")
    public UserRatesDTO getRates(){
        return new UserRatesDTO();
    }

}
