package com.example.system.services;

import com.example.system.dto.PeriodDTO;
import com.example.system.models.Period;
import com.example.system.models.PeriodKey;
import com.example.system.repositories.PeriodRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PeriodService {
    private final PeriodRepo periodRepo;
    private final ModelMapper modelMapper;

    @Autowired
    public PeriodService(PeriodRepo periodRepo, ModelMapper modelMapper) {
        this.periodRepo = periodRepo;
        this.modelMapper = modelMapper;
    }

    public List<PeriodDTO> getAllPeriods() {
        List<Period> periods = periodRepo.findAll();
        List<PeriodDTO> periodDTOS = new ArrayList<>();
        for (Period period : periods) {
            PeriodDTO periodDTO = modelMapper.map(period, PeriodDTO.class);
            periodDTOS.add(periodDTO);
        }
        return periodDTOS;
    }

    public Optional<PeriodDTO> getPeriodById(PeriodKey id) {
        Optional<Period> period = periodRepo.findById(id);
        if (period.isPresent()) {
            PeriodDTO periodDTO = modelMapper.map(period.get(), PeriodDTO.class);
            return Optional.of(periodDTO);
        } else {
            return Optional.empty();
        }
    }
}
