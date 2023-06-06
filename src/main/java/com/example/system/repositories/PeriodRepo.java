package com.example.system.repositories;

import com.example.system.models.Period;
import com.example.system.models.PeriodKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeriodRepo extends JpaRepository<Period, PeriodKey> {
}
