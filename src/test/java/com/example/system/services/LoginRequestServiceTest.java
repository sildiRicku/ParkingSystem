package com.example.system.services;

import com.example.system.models.UserCredentials;
import com.example.system.repositories.UserCredentialsRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class LoginRequestServiceTest {

    @InjectMocks
    private LoginRequestService loginRequestService;

    @Mock
    private UserCredentialsRepo userCredentialsRepo;

    @Mock
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void authenticateUserWithValidCredentials() {
        UserCredentials userCredentials = new UserCredentials();
        userCredentials.setEmail("test@example.com");
        userCredentials.setPassword("encodedPassword"); // Assuming this is encoded password
        when(userCredentialsRepo.findByEmail("test@example.com")).thenReturn(userCredentials);

        when(passwordEncoder.matches("password123", "encodedPassword")).thenReturn(true);

        boolean result = loginRequestService.authenticateUser("test@example.com", "password123");

        assertTrue(result);
    }

    @Test
    void authenticateUserWithInvalidUsername() {
        when(userCredentialsRepo.findByEmail(anyString())).thenReturn(null);

        boolean result = loginRequestService.authenticateUser("nonexistent@example.com", "password123");

        assertFalse(result);
    }

    @Test
    void authenticateUserWithInvalidPassword() {
        UserCredentials userCredentials = new UserCredentials();
        userCredentials.setEmail("test@example.com");
        userCredentials.setPassword("encodedPassword"); // Assuming this is encoded password
        when(userCredentialsRepo.findByEmail("test@example.com")).thenReturn(userCredentials);

        when(passwordEncoder.matches("incorrectPassword", "encodedPassword")).thenReturn(false);

        boolean result = loginRequestService.authenticateUser("test@example.com", "incorrectPassword");

        assertFalse(result);
    }
}
