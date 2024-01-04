package com.example.system.controllers;

import com.example.system.dto.LoginRequest;
import com.example.system.services.CustomUserDetailsService;
import com.example.system.services.LoginRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import com.example.system.security.JwtTokenUtil;
import com.example.system.security.JwtResponse;

@RestController
@RequestMapping("/authenticate")
@CrossOrigin
public class JwtAuthenticationController {

    @Autowired
    private LoginRequestService loginRequestService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private CustomUserDetailsService userDetailsService;

    @GetMapping("/a")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody LoginRequest authenticationRequest) throws Exception {
        boolean isAuthenticated = loginRequestService.authenticateUser(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        if (isAuthenticated) {
            final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
            final String token = jwtTokenUtil.generateToken(userDetails);
            return ResponseEntity.ok(new JwtResponse(token));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}

