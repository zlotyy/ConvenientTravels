package com.mvc.controller;

import com.mvc.model.UserModel;
import com.mvc.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller("homeController")
@RequestMapping("/")
public class HomeController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    IUserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public String index(Principal principal, HttpSession session){

        if(principal != null) {
            String login = principal.getName();
            UserModel user = userService.getUser(login);
            session.setAttribute("userFromSession", user);

            log.info("Uzytkownik " + session.getAttribute("userFromSession") + " zostal zalogowany");
        }

        log.info("returnStartPage");

        return "home/index";
    }
}
