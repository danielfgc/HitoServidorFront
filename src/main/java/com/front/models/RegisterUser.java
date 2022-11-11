package com.front.models;

import lombok.Data;

@Data
public class RegisterUser {
    private String username;
    private String email;
    private String password;

    public RegisterUser(){}
    public RegisterUser(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
}
