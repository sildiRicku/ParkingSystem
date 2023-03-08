package com.example.system.repositories;

import com.example.system.entities.Period;
import com.example.system.entities.PeriodKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PeriodRepo extends JpaRepository<Period, PeriodKey> {
}
