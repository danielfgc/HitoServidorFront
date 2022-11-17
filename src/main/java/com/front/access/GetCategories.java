package com.front.access;

import com.front.models.Category;
import org.springframework.web.client.RestTemplate;
import java.util.List;

public class GetCategories {
    public static Category[] getCategories() {
        String url = "http://localhost:8082/api/categories";
        RestTemplate restTemplate = new RestTemplate();
        Category[] categories = restTemplate.getForObject(url, Category[].class);
        return categories;

    }
}

