package com.example.system.services;

import com.example.system.exceptionhandlers.NotFoundException;
import com.example.system.models.UserCredentials;
import com.example.system.repositories.UserCredentialsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserCredentialsRepo userCredentialsRepo;

    public UserDetails loadUserByUsername(String username) throws NotFoundException {
        UserCredentials userCredentials = userCredentialsRepo.findByEmail(username);
        if (userCredentials == null) {
            throw new NotFoundException("User not found with email: " + username);
        }
        return User.builder().username(userCredentials.getEmail()).password(userCredentials.getPassword()).roles(userCredentials.getAuthority().getUserAuthority().toString()).build();
    }
}
