package com.front.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LogoutController {
    @RequestMapping("/Sign-out")
    public String signout(HttpServletRequest req){
        req.getSession().invalidate();
        return "index";
    }
}
