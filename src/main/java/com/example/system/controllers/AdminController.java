package com.example.system.controllers;

import com.example.system.models.Admin;
import com.example.system.models.UserLoginInfo;
import com.example.system.repositories.AdminRepo;
import com.example.system.repositories.UserDetailRepo;
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
    private UserDetailRepo userDetailRepo;
    @Autowired
    private AdminRepo adminRepo;

    @PostMapping("/addinfo")
    public UserLoginInfo addInfo(@RequestParam int adminId) {
        Admin admin = adminRepo.findById(adminId).get();
        UserLoginInfo loginInfo = new UserLoginInfo();
        loginInfo.setAdmin(admin);
        loginInfo.setEmail("sildi@gmail.com");
        loginInfo.setPassword(passwordEncoder.encode("password1"));
        userDetailRepo.save(loginInfo);
        return loginInfo;
    }
}

