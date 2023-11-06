package com.example.system.repositories;

import com.example.system.models.UserLoginInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailRepo extends JpaRepository<UserLoginInfo, Integer> {
    UserLoginInfo findByEmail(String email);
}
