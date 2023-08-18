package com.example.system.services;

import com.example.system.dto.RuleDTO;
import com.example.system.exceptionhandlers.NotFoundException;
import com.example.system.models.ParkingSystem;
import com.example.system.models.Rule;
import com.example.system.repositories.ParkingSystemRepo;
import com.example.system.repositories.RuleRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RuleService {
    private final RuleRepo ruleRepo;
    private final ParkingSystemRepo parkingSystemRepo;
    private final ModelMapper modelMapper;

    @Autowired
    public RuleService(RuleRepo ruleRepo, ParkingSystemRepo parkingSystemRepo, ModelMapper modelMapper) {
        this.ruleRepo = ruleRepo;
        this.parkingSystemRepo = parkingSystemRepo;
        this.modelMapper = modelMapper;
    }

    public List<RuleDTO> getAllRules() {
        List<Rule> rules = ruleRepo.findAll();
        List<RuleDTO> ruleDTOS = new ArrayList<>();
        for (Rule rule : rules) {
            RuleDTO ruleDTO = modelMapper.map(rule, RuleDTO.class);
            ruleDTOS.add(ruleDTO);
        }
        return ruleDTOS;
    }

    public List<RuleDTO> getRulesForParkingSystem(int parkingSystemId) {
        Optional<ParkingSystem> parkingSystem = parkingSystemRepo.findById(parkingSystemId);
        if (parkingSystem.isPresent()) {
            List<Rule> rules = parkingSystem.get().getRules();
            List<RuleDTO> ruleDTOS = new ArrayList<>();
            for (Rule rule : rules) {
                RuleDTO ruleDTO = modelMapper.map(rule, RuleDTO.class);
                ruleDTOS.add(ruleDTO);
            }
            return ruleDTOS;
        }
        throw new NotFoundException("Parking system not found");
    }
}
