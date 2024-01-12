package com.example.system.dto;

import lombok.Data;

@Data
public class LoginResponse {
    private boolean loggedIn;
    private String username;
}
