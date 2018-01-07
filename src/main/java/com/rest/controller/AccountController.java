package com.rest.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mvc.model.UserModel;
import com.mvc.service.IUserService;
import com.rest.helpers.TokenGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

@RestController("restController")
@RequestMapping("/rest")
public class AccountController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    IUserService userService;


//    @RequestMapping(value = "/login", method = RequestMethod.POST)
//    public ResponseEntity<HashMap<String, Long>> rest(@RequestBody HashMap<String, String> credentials){
//        log.info("RESTOWA METODA - LOGOWANIE");
//
//        String login = credentials.get("login");
//        String password = credentials.get("password");
//
//        UserModel user = userService.getUser(login, password).getData();
//
//        HashMap<String, Long> userId = new HashMap<>();
//        userId.put("userId", user.getUserId());
//
//        return new ResponseEntity<> (userId, new HttpHeaders(), HttpStatus.OK);
//    }


    /**
     * metoda sluzy do zalogowania uzytkownika android do aplikacji
     * zwraca ID uzytkownika + token, dzieki ktoremu przy kolejnych wywolaniach sprawdzamy czy uzytkownik android nadal ma sesje w aplikacji
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public String rest(@RequestParam(value = "login", required = false) String login,
                       @RequestParam(value = "password", required = false) String password,
                       HttpSession session){

        log.info("RESTOWA METODA - LOGOWANIE");
        String jsonOutput = "";
        HashMap<String, String> outputHashMap = new HashMap<>();
        // generujemy token dla aplikacji androidowej
        String token = TokenGenerator.generateToken(64);

        UserModel user = userService.getUser(login, password).getData();

        if(user != null){
            session.setAttribute("androidUserToken_userId_" + user.getUserId(), token);
            outputHashMap.put("userId", String.valueOf(user.getUserId()));
            outputHashMap.put("token", token);

            ObjectMapper mapper = new ObjectMapper();
            try {
                jsonOutput = mapper.writeValueAsString(outputHashMap);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }

        return jsonOutput;
    }


    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String testy(){
        log.info("REST - TESTY");

        return "testy";
    }
}
