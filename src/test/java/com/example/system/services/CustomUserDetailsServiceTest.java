package com.example.system.services;

import com.example.system.enums.UserAuthority;
import com.example.system.exceptionhandlers.NotFoundException;
import com.example.system.models.Authority;
import com.example.system.models.UserCredentials;
import com.example.system.repositories.UserCredentialsRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class CustomUserDetailsServiceTest {

    @InjectMocks
    private CustomUserDetailsService customUserDetailsService;

    @Mock
    private UserCredentialsRepo userCredentialsRepo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void loadUserByUsernameWithValidUser() throws NotFoundException {
        // Create Authority instance
        Authority authority = new Authority();
        authority.setId(1L); // Assuming you have a valid ID for the authority
        authority.setUserAuthority(UserAuthority.USER); // Set the user authority

        // Create UserCredentials instance
        UserCredentials userCredentials = new UserCredentials();
        userCredentials.setEmail("test@example.com");
        userCredentials.setPassword("encodedPassword"); // Assuming this is encoded password
        userCredentials.setAuthority(authority); // Set the authority

        // Mock the repository call
        when(userCredentialsRepo.findByEmail("test@example.com")).thenReturn(userCredentials);

        // Call the method under test
        UserDetails userDetails = customUserDetailsService.loadUserByUsername("test@example.com");

        // Assertions
        assertEquals("test@example.com", userDetails.getUsername());
        assertEquals("encodedPassword", userDetails.getPassword());
    }
    @Test
    void loadUserByUsernameWithInvalidUser() {
        when(userCredentialsRepo.findByEmail(anyString())).thenReturn(null);

        NotFoundException exception = assertThrows(NotFoundException.class,
                () -> customUserDetailsService.loadUserByUsername("nonexistent@example.com"));

        assertEquals("User not found with email: nonexistent@example.com", exception.getMessage());
    }
}
