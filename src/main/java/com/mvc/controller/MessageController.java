package com.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("messageController")
@RequestMapping("/messages")
public class MessageController {

    @RequestMapping("/myMessages")
    public String return_myMessages_index(){

        return "messages/myMessages/index";
    }

    @RequestMapping("/sendMessage")
    public String return_sendMessage_index(){

        return "messages/sendMessage/index";
    }
}
