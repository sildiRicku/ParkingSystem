package com.example.system.services;

import com.example.system.dto.LoginRequest;
import com.example.system.models.UserCredentials;
import com.example.system.repositories.UserCredentialsRepo;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Data
public class LoginService {
    @Autowired
    private UserCredentialsRepo userCredentialsRepo;

    public List<LoginRequest> getAll() {
        List<UserCredentials> userCredentials = userCredentialsRepo.findAll();
        List<LoginRequest> requests = new ArrayList<>();

        for (UserCredentials userCredentials1 : userCredentials) {
            LoginRequest loginRequest = converToDto(userCredentials1);
            requests.add(loginRequest);
        }
        return requests;
    }

    public LoginRequest converToDto(UserCredentials userCredentials) {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername(userCredentials.getEmail());
        loginRequest.setPassword(userCredentials.getPassword());
        return loginRequest;
    }
}
