package com.example.system.controllers;

import com.example.system.dto.LoginRequest;
import com.example.system.dto.LoginResponse;
import com.example.system.services.LoginRequestService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class LoginControllerTest {

    @InjectMocks
    private LoginController loginController;

    @Mock
    private LoginRequestService loginRequestService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void loginWithValidCredentials() {
        when(loginRequestService.authenticateUser("testUser", "password123")).thenReturn(true);

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("testUser");
        loginRequest.setPassword("password123");

        ResponseEntity<LoginResponse> responseEntity = loginController.login(loginRequest);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(responseEntity.getBody(), responseEntity.getBody());
    }

    @Test
    void loginWithInvalidCredentials() {
        when(loginRequestService.authenticateUser(anyString(), anyString())).thenReturn(false);

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("invalidUser");
        loginRequest.setPassword("invalidPassword");

        ResponseEntity<LoginResponse> responseEntity = loginController.login(loginRequest);

        assertEquals(HttpStatus.UNAUTHORIZED, responseEntity.getStatusCode());
        assertEquals(responseEntity.getBody(), responseEntity.getBody());
    }
}
