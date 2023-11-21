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
        // Mocking behavior to return a UserCredentials when findByEmail is called
        UserCredentials userCredentials = new UserCredentials();
        userCredentials.setEmail("test@example.com");
        userCredentials.setPassword("encodedPassword"); // Assuming this is encoded password
        when(userCredentialsRepo.findByEmail("test@example.com")).thenReturn(userCredentials);

        // Mocking behavior to return true when passwordEncoder.matches is called
        when(passwordEncoder.matches("password123", "encodedPassword")).thenReturn(true);

        // Performing the test
        boolean result = loginRequestService.authenticateUser("test@example.com", "password123");

        // Validating the result
        assertTrue(result);
    }

    @Test
    void authenticateUserWithInvalidUsername() {
        // Mocking behavior to return null when findByEmail is called
        when(userCredentialsRepo.findByEmail(anyString())).thenReturn(null);

        // Performing the test
        boolean result = loginRequestService.authenticateUser("nonexistent@example.com", "password123");

        // Validating the result
        assertFalse(result);
    }

    @Test
    void authenticateUserWithInvalidPassword() {
        // Mocking behavior to return a UserCredentials when findByEmail is called
        UserCredentials userCredentials = new UserCredentials();
        userCredentials.setEmail("test@example.com");
        userCredentials.setPassword("encodedPassword"); // Assuming this is encoded password
        when(userCredentialsRepo.findByEmail("test@example.com")).thenReturn(userCredentials);

        when(passwordEncoder.matches("incorrectPassword", "encodedPassword")).thenReturn(false);

        boolean result = loginRequestService.authenticateUser("test@example.com", "incorrectPassword");

        assertFalse(result);
    }
}
