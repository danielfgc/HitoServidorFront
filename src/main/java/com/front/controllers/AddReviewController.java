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
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Controller
public class AddReviewController {

    @RequestMapping("/Add-review")
    public ModelAndView addreview(ModelAndView mv, HttpServletResponse res){
        mv.addObject("review", new Review());
        mv.addObject("categories", GetCategories.getCategories());
        res.setHeader("Access-Control-Allow-Origin", "*");
        res.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        res.setHeader("Access-Control-Max-Age", "3600");
        res.setHeader("Access-Control-Allow-Headers", "x-requested-with, authorization, content-type");
        mv.setViewName("addreview");
        return mv;
    }
    @PostMapping(path="/createReview",consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = {
                    MediaType.APPLICATION_ATOM_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE
            })
    public @ResponseBody ModelAndView saveReview(ModelAndView mv, HttpServletRequest req, Review review, HttpServletResponse res) throws URISyntaxException, IOException, InterruptedException {
        String url = "http://localhost:8082/api/reviews";
        review.setUser((Integer) req.getSession().getAttribute("idUser"));
        review.setCategory((Integer) review.getCategory());
        review.setLikes(0);
        review.setDislikes(0);
        Gson gson = new Gson();
/*
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .setHeader("Authorization", "authToken:"+req.getSession().getAttribute("TOKEN"))
                .uri(new URI(url))
                .POST(HttpRequest.BodyPublishers.ofString(gson.toJson(review)))
                .build();
        HttpResponse<String> response = client.send(request,HttpResponse.BodyHandlers.ofString());*/
        req.setAttribute("Authorization","authToken:"+req.getSession().getAttribute("TOKEN"));
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION,"authToken:"+req.getSession().getAttribute("TOKEN"));
        headers.setBearerAuth("authToken: "+req.getSession().getAttribute("TOKEN"));
        headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> entity = new HttpEntity<>(gson.toJson(review), headers);
        restTemplate.postForObject(url, entity,String.class);
        mv.setViewName("addreview");
        return mv;
    }
}
