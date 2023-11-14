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
    public UserLoginInfo addInfo(@RequestParam int adminId, @RequestParam String username, @RequestParam String password) {
        Admin admin = adminRepo.findById(adminId).get();
        UserLoginInfo loginInfo = new UserLoginInfo();
        loginInfo.setEmail(username);
        loginInfo.setPassword(passwordEncoder.encode(password));
        userDetailRepo.save(loginInfo);
        admin.setLoginInfo(loginInfo);
        adminRepo.save(admin);
        return loginInfo;
    }
}

