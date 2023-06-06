package com.example.system.repositories;

import com.example.system.models.Holiday;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HolidayRepo extends JpaRepository<Holiday, Integer> {
}
