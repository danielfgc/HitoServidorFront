package com.front.controllers;

import com.front.models.RegisterUser;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class RegisterController {
    @RequestMapping("/Register")
    public ModelAndView register(ModelAndView mv){
        mv.addObject("registerUser", new RegisterUser());
        mv.setViewName("Register");
        return mv;
    }
    @PostMapping(path="/registrarUser",consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = {
                    MediaType.APPLICATION_ATOM_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE
            })
    public @ResponseBody ModelAndView registrado(ModelAndView mv, RegisterUser request){
        String url = "http://localhost:8082/api/register";
        RestTemplate restTemplate = new RestTemplate();
        String respuesta = restTemplate.postForObject(url, request,String.class);
        mv.addObject("respuesta",respuesta);
        mv.setViewName("Register");
        return mv;
    }
}
