package com.example.system.controllers;

import com.example.system.dto.RuleDTO;
import com.example.system.entities.Rule;
import com.example.system.services.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rule")
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

    @GetMapping("/{id}")
    public RuleDTO getRuleById(@PathVariable int id) {
        return ruleService.getRuleById(id).orElse(null);
    }

    @PostMapping("/new")
    public Rule addRule(RuleDTO ruleDTO) {
        return ruleService.addRule(ruleDTO);
    }
}
