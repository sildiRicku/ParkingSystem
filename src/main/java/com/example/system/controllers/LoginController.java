package com.example.system.controllers;

import com.example.system.dto.LoginRequest;
import com.example.system.services.LoginService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/login")
@CrossOrigin
@Data
public class LoginController {
    @Autowired
    private LoginService loginService;

    @GetMapping("")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        List<LoginRequest> loginRequests = loginService.getAll();
        if (loginRequests.contains(loginRequest)) {
            return ResponseEntity.ok("Login Succesful");
        } else return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not Found");
    }


}
