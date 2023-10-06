package com.example.system.controllers;

import com.example.system.dto.ParkingSystemDTO;
import com.example.system.models.ParkingSystem;
import com.example.system.services.ParkingSystemService;
import com.example.system.services.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rules")
public class RuleController {
    private final RuleService ruleService;
    private final ParkingSystemService parkingSystemService;

    @Autowired
    public RuleController(RuleService ruleService, ParkingSystemService parkingSystemService) {
        this.ruleService = ruleService;
        this.parkingSystemService = parkingSystemService;
    }

    @GetMapping("/check-rule-conflict/")
    public ResponseEntity<String> checkRuleConflict(@RequestParam int systemId) {

        ParkingSystemDTO parkingSystem = parkingSystemService.getParkingSystemById(systemId).get();

        boolean hasConflict = ruleService.checkRuleConflict(parkingSystem.getRules());

        if (hasConflict) {
            return ResponseEntity.ok("Rule conflict detected.");
        } else {
            return ResponseEntity.ok("No rule conflicts.");
        }
    }

}
