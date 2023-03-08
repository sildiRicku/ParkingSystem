package com.example.system.services;

import com.example.system.entities.Period;
import com.example.system.entities.PeriodKey;
import com.example.system.repositories.PeriodRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PeriodService {
    private final PeriodRepo periodRepo;

    public PeriodService(PeriodRepo periodRepo) {
        this.periodRepo = periodRepo;
    }

    public List<Period> getAllPeriods() {
        return periodRepo.findAll();
    }

    public Optional<Period> getPeriodById(PeriodKey id) {
        return periodRepo.findById(id);
    }

}
