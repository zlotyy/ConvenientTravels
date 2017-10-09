package com.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller("httpErrorHandler")
@RequestMapping("error")
public class HTTPErrorHandler {


    //for 403 access denied page
    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String accessDenied() {

//        ModelAndView model = new ModelAndView();

        //check if user is login
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        if (!(auth instanceof AnonymousAuthenticationToken)) {
//            UserDetails userDetail = (UserDetails) auth.getPrincipal();
//            model.addObject("username", userDetail.getUsername());
//        }

//        model.setViewName("403/index");
        return "error/403/index";
    }

//    @RequestMapping(value="/404", method = RequestMethod.GET)
//    public String error404(){
//
//        return "error/404/index";
//    }
}
