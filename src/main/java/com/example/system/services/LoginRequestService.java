package com.example.system.services;

import com.example.system.dto.LoginRequest;
import com.example.system.models.UserCredentials;
import com.example.system.repositories.UserCredentialsRepo;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@Data
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

    public LoginRequest converToDto(UserCredentials userCredentials) {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername(userCredentials.getEmail());
        loginRequest.setPassword(userCredentials.getPassword());
        return loginRequest;
    }
}
