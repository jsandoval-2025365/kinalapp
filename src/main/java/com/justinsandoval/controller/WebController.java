package com.justinsandoval.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class WebController {

    @RequestMapping("/login")
    public String login(){
        return "/login";
    }
}
