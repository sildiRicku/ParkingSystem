package com.example.system.repositories;

import com.example.system.entities.ParkingSystem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingSystemRepo extends JpaRepository<ParkingSystem, Integer> {

}
