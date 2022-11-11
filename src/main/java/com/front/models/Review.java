package com.front.models;

import lombok.Data;

@Data
public class Review {
    private int id;
    private User user;
    private Category category;
    private String title;
    private String body;
    private float valoration;
    private int likes;
    private int dislikes;

}
