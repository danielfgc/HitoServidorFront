package com.front.models.auth;

import lombok.Data;

@Data
public class AuthResponse {
    private int id;
    private String username;
    private String email;
    private String accessToken;

}