package com.example.system.repositories;

import com.example.system.entities.Holiday;
import com.example.system.entities.ParkingSystem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HolidayRepo extends JpaRepository<Holiday, Integer> {
}
