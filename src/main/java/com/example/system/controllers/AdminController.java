package com.example.system.controllers;

import com.example.system.models.Admin;
import com.example.system.models.UserCredentials;
import com.example.system.repositories.AdminRepo;
import com.example.system.repositories.UserCredentialsRepo;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserCredentialsRepo userCredentialsRepo;
    @Autowired
    private AdminRepo adminRepo;

    @PostMapping("/addinfo")
    public UserCredentials addInfo(@RequestParam int adminId, @RequestParam String username, @RequestParam String password) {
        Admin admin = adminRepo.findById(adminId).get();
        UserCredentials loginInfo = new UserCredentials();
        loginInfo.setEmail(username);
        loginInfo.setPassword(passwordEncoder.encode(password));
        userCredentialsRepo.save(loginInfo);
        admin.setLoginInfo(loginInfo);
        adminRepo.save(admin);
        return loginInfo;
    }
}

