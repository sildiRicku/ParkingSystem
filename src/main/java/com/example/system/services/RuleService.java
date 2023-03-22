package com.example.system.services;

import com.example.system.dto.ParkingSystemDTO;
import com.example.system.dto.RuleDTO;
import com.example.system.entities.Rule;
import com.example.system.repositories.RuleRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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

    public String getHoursForMoney(double money, ParkingSystemDTO parkingSystemDTO) {
        Rule activeRule = null;
        for (Rule rule : parkingSystemDTO.getRules()) {
            LocalDateTime now = LocalDateTime.now();
            LocalTime startTime = rule.getStartTime().toLocalTime();
            LocalTime endTime = rule.getEndTime().toLocalTime();
            if (now.toLocalTime().isAfter(startTime) && now.toLocalTime().isBefore(endTime)) {
                activeRule = rule;
                break;

            }
        }

        if (activeRule == null) {
            return "Technical Error";
        }
        double costPerHour = activeRule.getCost();
        double hours = money / costPerHour;
        long minutes = (long) (hours * 60);
        LocalTime endingTime = LocalTime.now().plusMinutes(minutes);
        if (endingTime.isAfter(activeRule.getEndTime().toLocalTime())) {
            Duration duration = Duration.between(activeRule.getEndTime().toLocalTime(), endingTime);
            long difference = duration.toMinutes();
            endingTime = activeRule.getStartTime().toLocalTime().plusMinutes(difference);
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        return "You can park until " + endingTime.format(formatter);
    }

}
