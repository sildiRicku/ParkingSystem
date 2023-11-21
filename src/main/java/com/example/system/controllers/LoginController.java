package com.example.system.controllers;

import com.example.system.dto.LoginRequest;
import com.example.system.services.LoginRequestService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/login")
@CrossOrigin
@Data
public class LoginController {
    @Autowired
    private LoginRequestService loginRequestService;

    @GetMapping("")
    public ResponseEntity<String> loginn(@RequestBody LoginRequest loginRequest) {

        boolean isAuthenticated = loginRequestService.authenticateUser(loginRequest.getUsername(), loginRequest.getPassword());

        if (isAuthenticated) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

}
