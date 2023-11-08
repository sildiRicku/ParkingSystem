package com.example.system.services;

import com.example.system.exceptionhandlers.NotFoundException;
import com.example.system.models.UserLoginInfo;
import com.example.system.repositories.UserDetailRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDetailService {
    @Autowired
    private UserDetailRepo userDetailRepo;

    public UserLoginInfo loadUserByUsername(String username) throws NotFoundException {
        UserLoginInfo userLoginInfo = userDetailRepo.findByEmail(username);
        if (userLoginInfo == null) {
            throw new NotFoundException("User not found with email: " + username);
        }
        return userLoginInfo;
    }
}
