package com.example.system.services;

import com.example.system.models.UserCredentials;
import com.example.system.repositories.UserCredentialsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class LoginRequestService {
    @Autowired
    private UserCredentialsRepo userCredentialsRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean authenticateUser(String username, String password) {
        UserCredentials userCredentials = userCredentialsRepo.findByEmail(username);

        if (userCredentials != null) {
            return passwordEncoder.matches(password, userCredentials.getPassword());
        }
        return false;
    }
}
