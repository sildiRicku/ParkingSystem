package com.example.system.services;

import com.example.system.dto.RuleDTO;
import com.example.system.models.ParkingSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RuleService {
    private final ParkingSystemService parkingSystemService;

    @Autowired
    public RuleService(ParkingSystemService parkingSystemService) {
        this.parkingSystemService = parkingSystemService;
    }

    public boolean checkRuleConflict(ParkingSystem parkingSystem) {
        List<RuleDTO> rules = parkingSystemService.getRulesForParkingSystem(parkingSystem.getSystemId());
        return true;
    }

}
