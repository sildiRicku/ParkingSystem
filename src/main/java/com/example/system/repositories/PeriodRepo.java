package com.example.system.repositories;

import com.example.system.entities.ParkingSystem;
import com.example.system.entities.Period;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PeriodRepo extends JpaRepository<Period, Integer> {
}
