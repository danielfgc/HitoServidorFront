package com.front.controllers;

import com.front.access.GetCategories;
import com.front.models.RegisterUser;
import com.front.models.Review;
import com.google.gson.Gson;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AddReviewController {

    @RequestMapping("/Add-review")
    public ModelAndView addreview(ModelAndView mv, HttpServletRequest req){
        mv.addObject("review", new Review());
        mv.addObject("categories", GetCategories.getCategories());
        mv.setViewName("addreview");
        return mv;
    }
    @PostMapping(path="/createReview",consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = {
                    MediaType.APPLICATION_ATOM_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE
            })
    public @ResponseBody ModelAndView saveReview(ModelAndView mv,HttpServletRequest req, Review request){
        String url = "http://localhost:8082/api/reviews";
        request.setUser((Integer) req.getSession().getAttribute("idUser"));
        request.setCategory((Integer) request.getCategory());
        request.setLikes(0);
        request.setDislikes(0);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization","authToken:"+req.getSession().getAttribute("TOKEN"));
        headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
        Gson gson = new Gson();
        HttpEntity<String> entity = new HttpEntity<>(gson.toJson(request), headers);
        restTemplate.postForObject(url, entity,String.class);
        mv.setViewName("addreview");
        return mv;
    }
}
