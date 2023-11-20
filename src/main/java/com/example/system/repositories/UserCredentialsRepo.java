package com.example.system.repositories;

import com.example.system.models.UserCredentials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserCredentialsRepo extends JpaRepository<UserCredentials, Integer> {
    UserCredentials findByEmail(String email);

    @Override
    List<UserCredentials> findAll();
}
