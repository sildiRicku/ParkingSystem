package com.example.system.repositories;

import com.example.system.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepo extends JpaRepository<Admin, Integer> {
    Admin findAdminByLoginInfoEmail(String email);
}
