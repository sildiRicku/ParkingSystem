package com.example.system.repositories;

import com.example.system.models.ParkingSystem;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParkingSystemRepo extends JpaRepository<ParkingSystem, Integer> {
    List<ParkingSystem> findAll(Sort sort);

}
