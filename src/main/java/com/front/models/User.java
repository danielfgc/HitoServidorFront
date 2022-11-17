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


}
