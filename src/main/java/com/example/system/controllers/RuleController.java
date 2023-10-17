package com.example.system.controllers;

import com.example.system.dto.ParkingSystemDTO;
import com.example.system.services.ParkingSystemService;
import com.example.system.services.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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

    @GetMapping("/check-conflict/")
    public ResponseEntity<String> checkRuleConflict(@RequestParam int parkingSystemId) {

        Optional<ParkingSystemDTO> optionalParkingSystem = parkingSystemService.getParkingSystemById(parkingSystemId);
        if (optionalParkingSystem.isPresent()) {
            ParkingSystemDTO parkingSystem = optionalParkingSystem.get();


            boolean hasConflict = ruleService.checkRuleConflict(parkingSystem.getRules());

            if (hasConflict) {
                return ResponseEntity.ok("Rule conflict detected.");
            } else {
                return ResponseEntity.ok("No rule conflicts.");
            }
        } else return ResponseEntity.notFound().build();
    }

}
