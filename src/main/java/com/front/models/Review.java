package com.front.models;

import lombok.Data;

@Data
public class Review {
    private int id;
    private int user;
    private int category;
    private String title;
    private String body;
    private float valoration;
    private int likes;
    private int dislikes;

}
