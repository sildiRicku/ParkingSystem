package com.example.system.repositories;

import com.example.system.entities.Holiday;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HolidayRepo extends JpaRepository<Holiday, Integer> {
}
