package com.mvc.controller;

import com.mvc.dto.PasswordDTO;
import com.mvc.dto.UserRatesDTO;
import com.mvc.helpers.ServiceResult;
import com.mvc.model.UserModel;
import com.mvc.service.ICarService;
import com.mvc.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;


@SessionAttributes(types = UserModel.class)     //potrzebne zeby przesylac obiekty miedzy kontrolerami gdy jest sesja
@Controller("userController")
@RequestMapping("/user")
public class UserController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    IUserService userService;

    @Autowired
    ICarService carService;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * kontroler przenosi do widoku rejestracji konta
     */
    @RequestMapping(value="/register", method = RequestMethod.GET)
    public String return_register_index(Model model, @RequestParam(value = "carModalVisible", required = false) String carModalVisible){
        UserModel user = new UserModel();
        model.addAttribute("user", user);

        log.info("return_register_index");

        if(carModalVisible != null){
            model.addAttribute("carModalVisible", true);
        }


        RestTemplate restTemplate = new RestTemplate();
        UserModel userRest = restTemplate.getForObject("http://localhost:8080/rest/test/1", UserModel.class);
        log.info("REST ODEBRANY OBIEKT " + userRest);

        return "register/index";
    }

    /**
     * kontroler wywoluje serwis zapisujacy uzytkownika do bazy
     */
    @RequestMapping(value="/register", method = RequestMethod.POST)
    public String createUser(@ModelAttribute("user") @Valid UserModel user, BindingResult bindingResult, HttpSession session, Model model){
        if(bindingResult.hasErrors()){
            log.info("Rejestracja konta - wprowadzono niepoprawne dane, zwroc formularz");
            return "register/index";
        } else {
            ServiceResult<UserModel> result;

            log.info("Rejestracja konta - dane poprawne, wywolaj serwis zapisujacy do bazy");

            result = userService.createUser(
                        user.getLogin(),
                        user.getPassword(),
                        user.getMail(),
                        user.getPhone(),
                        user.getName(),
                        user.getLastname(),
                        user.getMale(),
                        user.getBirthDate(),
                        user.getCars()
            );

            if(result.isValid()){
                log.info("Rejestracja konta - uzytkownik zapisany do bazy");
                return "redirect:/?registerSuccess";
            } else {
                log.info("Rejestracja konta - nie udalo sie zapisac uzytkownika do bazy");

                session.setAttribute("dbMessage", result.errorsToString());                                   //lista bledow
                model.addAttribute("dbError", true);

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
//        UserRatesDTO userRatesDTO = new UserRatesDTO();
////        UserRatesCounter uRC = new UserRatesCounter();
////        userRatesDTO.setUserRates(uRC.returnAverageRate(user.getUserRates()));
////        userRatesDTO.setDrivingSkills(uRC.returnAverageRate(user.getDrivingSkills()));
//        userRatesDTO.setUserRates(5);

        model.addAttribute("user", user);
//        model.addAttribute("userRates", userRatesDTO);

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
    public String editUser(@ModelAttribute("user") @Valid UserModel user, BindingResult bindingResult, HttpSession session, Model model){
        if(bindingResult.hasErrors()){
            log.info("Edycja konta - wprowadzono niepoprawne dane, zwroc formularz");

            return "account/index";
        } else {
            ServiceResult<UserModel> result;

            log.info("Edycja konta - dane poprawne, wywolaj serwis zapisujacy do bazy");

            result = userService.editUser(user);

            if(result.isValid()){
                log.info("Edycja konta - uzytkownik zapisany do bazy");
                user = userService.getUser(user.getUserId()).getData();
                session.setAttribute("userFromSession", user);
                return "redirect:/user/account";
            } else {
                log.info("Edycja konta - nie udalo sie zapisac uzytkownika do bazy");

                user = userService.getUser(user.getUserId()).getData();
                session.setAttribute("userFromSession", user);
                session.setAttribute("dbMessage", result.errorsToString());                                   //lista bledow
                model.addAttribute("dbError", true);

                return "account/index";
            }
        }
    }


    /**
     * kontroler wywoluje serwis edytujacy haslo uzytkownika
     */
    @RequestMapping(value = "/account/changePassword", method = RequestMethod.POST)
    public String editPassword(@ModelAttribute("userPassword") @Valid PasswordDTO passwordDTO, BindingResult bindingResult, @ModelAttribute("user") @Valid UserModel user){
        if(bindingResult.hasErrors()){
            log.info("Edycja hasla - wprowadzono niepoprawne dane, zwroc formularz");

            return "account/index";
        } else {
            if(bCryptPasswordEncoder.matches(passwordDTO.getOldPassword(), user.getPassword())){
                ServiceResult<UserModel> result;

                log.info("Edycja hasla - wywolaj serwis zapisujacy do bazy");

                result = userService.editPassword(user, passwordDTO.getNewPassword());

                if(result.isValid()){
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
    @RequestMapping(value = "/account/delete/confirm/confirmed", method = RequestMethod.POST)
    public String deleteUser(@ModelAttribute("user") @Valid UserModel user, HttpServletRequest request){
        ServiceResult<UserModel> result;

        log.info("Usuwanie konta - wywolaj serwis zmieniajacy isDeleted na true");

        result = userService.setUserDeleted(user);

        if(result.isValid()){
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
        model.addAttribute("dialogFormAction", "/user/account/delete/confirm/confirmed");
        model.addAttribute("dialogFormName", "deleteConfirmForm");

        return "modals/confirm";
    }

    /**
     * kontroler wyswietla modal z alertem
     */
    @RequestMapping(value = {"/account/changePassword/alert", "/register/wrongPassword/alert"}, method = RequestMethod.GET)
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
