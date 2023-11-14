package com.example.system.services;

import com.example.system.exceptionhandlers.NotFoundException;
import com.example.system.models.UserLoginInfo;
import com.example.system.repositories.UserDetailRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDetailRepo userDetailRepo;

    public UserDetails loadUserByUsername(String username) throws NotFoundException {
        UserLoginInfo userLoginInfo = userDetailRepo.findByEmail(username);
        if (userLoginInfo == null) {
            throw new NotFoundException("User not found with email: " + username);
        }
        return User.builder().username(userLoginInfo.getEmail()).password(userLoginInfo.getPassword()).roles("USER").build();
    }
}
