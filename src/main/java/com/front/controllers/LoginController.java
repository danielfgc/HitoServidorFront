package com.front.controllers;

import com.front.models.auth.AuthRequest;
import com.front.models.auth.AuthResponse;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {
    @RequestMapping("/Sign-in")
    public ModelAndView login(ModelAndView mv){
        mv.addObject("authRequest", new AuthRequest());
        mv.setViewName("login");
        return mv;
    }
    @PostMapping(path="/authenticate",consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = {
                    MediaType.APPLICATION_ATOM_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE
            })
    public @ResponseBody ModelAndView signin(ModelAndView mv, AuthRequest request, HttpServletRequest req){
        String url = "http://localhost:8082/api/login";
        RestTemplate restTemplate = new RestTemplate();
        try {
            AuthResponse respuesta = restTemplate.postForObject(url, request, AuthResponse.class);
            req.getSession().setAttribute("TOKEN", respuesta.getAccessToken());
            req.getSession().setAttribute("idUser", respuesta.getId());
            mv.setViewName("index");
        }catch (HttpClientErrorException.Unauthorized e){
            String error = "Usuario o contraseña incorrectos";
            mv.addObject("error",error);
            mv.setViewName("login");
        }
        return mv;
    }
}
