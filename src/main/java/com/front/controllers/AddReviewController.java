package com.front.controllers;

import com.front.access.GetCategories;
import com.front.models.RegisterUser;
import com.front.models.Review;
import com.google.gson.Gson;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
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
    public @ResponseBody ModelAndView saveReview(ModelAndView mv, HttpServletRequest req, Review review) throws Exception {
        String url = "http://localhost:8082/api/reviews";
        review.setId(null);
        review.setIduser((Integer) req.getSession().getAttribute("idUser"));
        review.setIdcategory(review.getIdcategory());
        review.setLikes(0);
        review.setDislikes(0);
        Gson gson = new Gson();

/*        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .header("Authorization", "authToken:"+req.getSession().getAttribute("TOKEN"))
                .header("Content-Type","application/json")
                .uri(new URI(url))
                .POST(HttpRequest.BodyPublishers.ofString(gson.toJson(review)))
                .build();
        HttpResponse<String> response = client.send(request,HttpResponse.BodyHandlers.ofString());
        System.out.println(response);
       req.setAttribute("Authorization","authToken:"+req.getSession().getAttribute("TOKEN"));*/
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION,"authToken:"+req.getSession().getAttribute("TOKEN"));
        headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
        System.out.println(gson.toJson(review));
        HttpEntity<String> entity = new HttpEntity<>(gson.toJson(review), headers);
        System.out.println(entity);
        ResponseEntity<Review> response = restTemplate.postForEntity(url, entity,Review.class);
        mv.addObject("response", response);
        System.out.println(response);

        mv.setViewName("index");
        return mv;
    }
}
