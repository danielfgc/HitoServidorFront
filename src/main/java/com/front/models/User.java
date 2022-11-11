package com.front.models;

import lombok.Data;

import java.util.List;

@Data
public class User {
    private int id;
    private String username;
    private String email;
    private String password;
    private List<Review> reviews;

    public User(int id, String username, String email, String password, List<Review> reviews) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.reviews = reviews;
    }
}
