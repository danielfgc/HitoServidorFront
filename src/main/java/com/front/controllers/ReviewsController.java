package com.front.controllers;
import com.front.models.Review;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;

@Controller
public class ReviewsController {
    @RequestMapping(path="/Reviews")
    public @ResponseBody ModelAndView registrado(ModelAndView mv){
        String url = "http://localhost:8082/api/reviews";
        RestTemplate restTemplate = new RestTemplate();
        Review[] respuesta = restTemplate.getForObject(url,Review[].class);
        List<Review> reviews = Arrays.asList(respuesta);
        mv.addObject("reviews",reviews);
        mv.setViewName("reviews");
        return mv;
    }
}
