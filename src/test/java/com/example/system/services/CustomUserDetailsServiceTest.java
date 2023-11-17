package com.example.system.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.*;

import com.example.system.exceptionhandlers.NotFoundException;
import com.example.system.models.UserCredentials;
import com.example.system.repositories.UserCredentialsRepo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UserDetails;

@RunWith(MockitoJUnitRunner.class)
public class CustomUserDetailsServiceTest {

    @Mock
    private UserCredentialsRepo userCredentialsRepo;

    @InjectMocks
    private CustomUserDetailsService customUserDetailsService;

    @Before
    public void setUp() {
    }

    @Test
    public void testLoadUserByUsername() {
        String username = "test@example.com";
        UserCredentials userCredentials = new UserCredentials();
        userCredentials.setEmail(username);
        userCredentials.setPassword("password123");

        when(userCredentialsRepo.findByEmail(username)).thenReturn(userCredentials);

        UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);

        assertEquals(username, userDetails.getUsername());
        assertEquals("password123", userDetails.getPassword());
        assertEquals("ROLE_USER", userDetails.getAuthorities().iterator().next().getAuthority());

        verify(userCredentialsRepo, times(1)).findByEmail(username);
    }

    @Test
    public void testLoadUserByUsernameNullUsername() {
        String username = null;

        assertThrows(NotFoundException.class, () -> customUserDetailsService.loadUserByUsername(username));

        verifyNoInteractions(userCredentialsRepo);
    }

    @Test(expected = NotFoundException.class)
    public void testLoadUserByUsernameUserNotFound() {
        String username = "nonexistent@example.com";

        when(userCredentialsRepo.findByEmail(username)).thenReturn(null);

        customUserDetailsService.loadUserByUsername(username);
    }
}

