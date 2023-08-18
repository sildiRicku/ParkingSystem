package com.example.system.controllers;

import com.example.system.dto.RuleDTO;
import com.example.system.services.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rule")
@CrossOrigin
public class RuleController {
    private final RuleService ruleService;

    @Autowired
    public RuleController(RuleService ruleService) {
        this.ruleService = ruleService;
    }

    @GetMapping("/all")
    public List<RuleDTO> getAllRules() {
        return ruleService.getAllRules();
    }

    @GetMapping("/parking-systems/{id}/rules")
    public ResponseEntity<List<RuleDTO>> getRulesForParkingSystem(@PathVariable int id) {
        List<RuleDTO> rules = ruleService.getRulesForParkingSystem(id);
        return ResponseEntity.ok(rules);
    }

}
