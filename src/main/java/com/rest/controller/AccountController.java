package com.rest.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mvc.dto.DriveDTO;
import com.mvc.model.UserModel;
import com.mvc.service.IUserService;
import com.rest.helpers.EmptyJsonResponse;
import com.rest.helpers.TokenGenerator;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@SessionAttributes(types = UserModel.class)     //potrzebne zeby przesylac obiekty miedzy kontrolerami gdy jest sesja
@RestController("restAccountController")
@RequestMapping("/rest")
public class AccountController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    IUserService userService;




    /**
     * metoda sluzy do zalogowania uzytkownika android do aplikacji
     * zwraca ID uzytkownika + token, dzieki ktoremu przy kolejnych wywolaniach sprawdzamy czy uzytkownik android nadal ma sesje w aplikacji
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody HashMap<String, String> requestHashMap,
                                  HttpSession session){

        String login = requestHashMap.get("login");
        String password = requestHashMap.get("password");

        log.info("RESTOWA METODA - LOGOWANIE");

        UserModel user = userService.getUser(login, password).getData();

        if(user != null) {
            log.info("REST - logowanie poprawne");
            HashMap<String, String> responseHashMap = new HashMap<>();
            // generujemy token dla aplikacji androidowej
            String token = TokenGenerator.generateToken(64);

            // dodaj token uzytkownika do sesji
            List<String> restTokensList = (ArrayList<String>) session.getAttribute("session_restTokensList");
            if(restTokensList == null){
                restTokensList = new ArrayList<>();
            }
            restTokensList.add(token);
            session.setAttribute("session_restTokensList", restTokensList);

            responseHashMap.put("userId", String.valueOf(user.getUserId()));
            responseHashMap.put("token", token);

            return new ResponseEntity<>(responseHashMap, HttpStatus.OK);
        } else {
            log.info("REST - bledne dane logowania");
            return new ResponseEntity<>(new EmptyJsonResponse(), HttpStatus.UNAUTHORIZED);
        }
    }
}
