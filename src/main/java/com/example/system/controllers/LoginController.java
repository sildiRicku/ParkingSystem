package com.example.system.controllers;

import com.example.system.dto.LoginRequest;
import com.example.system.dto.LoginResponse;
import com.example.system.services.CustomUserDetailsService;
import com.example.system.services.LoginRequestService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/login")
@CrossOrigin()
@Data
public class LoginController {
    @Autowired
    private LoginRequestService loginRequestService;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;


    @PostMapping("")

    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {

        boolean isAuthenticated = loginRequestService.authenticateUser(loginRequest.getUsername(), loginRequest.getPassword());

        if (isAuthenticated) {
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.setContentType(MediaType.APPLICATION_JSON);

            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setLoggedIn(true);
            loginResponse.setUsername(loginRequest.getUsername());

            UserDetails credentials = customUserDetailsService.loadUserByUsername(loginRequest.getUsername());

            if (credentials != null && credentials.getAuthorities() != null) {
                loginResponse.setAuthority(credentials.getAuthorities().toString());
            } else {
                loginResponse.setAuthority("DEFAULT_AUTHORITY");
            }

            return ResponseEntity.ok().headers(responseHeaders).body(loginResponse);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new LoginResponse());
        }

    }

}
