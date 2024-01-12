package com.example.system.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}
