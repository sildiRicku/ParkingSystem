package com.example.system.services;

import com.example.system.entities.Rule;
import com.example.system.repositories.RuleRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RuleService {
    private final RuleRepo ruleRepo;

    public RuleService(RuleRepo ruleRepo) {
        this.ruleRepo = ruleRepo;
    }

    public List<Rule> getAllRules() {
        return ruleRepo.findAll();
    }

    public Optional<Rule> getRuleById(int id) {
        return ruleRepo.findById(id);
    }

}
