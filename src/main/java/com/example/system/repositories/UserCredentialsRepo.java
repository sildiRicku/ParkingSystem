package com.example.system.repositories;

import com.example.system.models.UserCredentials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCredentialsRepo extends JpaRepository<UserCredentials, Integer> {
    UserCredentials findByEmail(String email);
}
