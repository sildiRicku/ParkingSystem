package com.example.system.services;

import com.example.system.dto.RuleDTO;
import com.example.system.entities.ParkingSystem;
import com.example.system.entities.Rule;
import com.example.system.repositories.RuleRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class RuleService {
    private final RuleRepo ruleRepo;
    private final ModelMapper modelMapper;

    @Autowired
    public RuleService(RuleRepo ruleRepo, ModelMapper modelMapper) {
        this.ruleRepo = ruleRepo;
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

    public Optional<RuleDTO> getRuleById(int id) {
        Optional<Rule> rule = ruleRepo.findById(id);
        if (rule.isPresent()) {
            RuleDTO ruleDTO = modelMapper.map(rule.get(), RuleDTO.class);
            return Optional.of(ruleDTO);
        } else {
            return Optional.empty();
        }
    }

    public RuleDTO addRule(RuleDTO ruleDTO) {
        Rule rule = modelMapper.map(ruleDTO, Rule.class);
        ruleRepo.save(rule);
        ruleDTO = modelMapper.map(rule, RuleDTO.class);
        return ruleDTO;
    }

    public String getHoursForMoney(int money, ParkingSystem parkingSystem) {
        Rule activeRule = null;
        for (Rule rule : parkingSystem.getRules()) {
            Date now = new Date();
            Time startTime = rule.getStartTime();
            Time endTime = rule.getEndTime();
            if (now.after(startTime) && now.before(endTime)) {
                activeRule = rule;
                break;
            }
        }

        if (activeRule == null) {
            return "Parking is currently closed";
        }

        double costPerHour = activeRule.getCost();
        double hours = money / costPerHour;

        return "You can park for " + hours + " hour(s)";
    }

}
