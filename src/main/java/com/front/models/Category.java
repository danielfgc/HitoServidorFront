package com.front.models;

import lombok.Data;

import java.util.List;

@Data
public class Category {
    private int id;
    private String category;

    private List<Review> reviews;

    public Category(int id, String category, List<Review> reviews) {
        this.id = id;
        this.category = category;
        this.reviews = reviews;
    }
}
