package com.front.models;

import lombok.Data;

import java.util.List;

@Data
public class Category {
    private int id;
    private String category;

    private List<Review> reviews;

}
