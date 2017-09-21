package com.mvc.controller;

import com.mvc.model.UserModel;
import com.mvc.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller("loginController")
@RequestMapping("/login")           // TAK W ZASADZIE TO JEST TO NIEPOTRZEBNE
public class LoginController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    IUserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public String login() {

        return "home/index";
    }
}
