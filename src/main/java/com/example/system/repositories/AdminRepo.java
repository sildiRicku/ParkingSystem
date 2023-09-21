package com.example.system.repositories;

import com.example.system.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepo extends JpaRepository<Admin, Integer> {
    Admin findAdminByEmail(String email);
}
