package com.example.system.controllers;

import com.example.system.dto.RuleDTO;
import com.example.system.entities.Rule;
import com.example.system.services.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

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
        Optional<RuleDTO> ruleDTO = ruleService.getRuleById(id);
        if (ruleDTO.isPresent()) {
            return ruleDTO.get();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/new")
    public Rule addRule(RuleDTO ruleDTO) {
        return ruleService.addRule(ruleDTO);
    }
}
