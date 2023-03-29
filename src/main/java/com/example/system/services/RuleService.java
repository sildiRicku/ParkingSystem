package com.example.system.services;

import com.example.system.dto.ParkingSystemDTO;
import com.example.system.dto.RuleDTO;
import com.example.system.entities.Rule;
import com.example.system.entities.TransactionPaymentType;
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

import static com.example.system.entities.TransactionPaymentType.*;


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


    public double calculateDailyCost(ParkingSystemDTO parkingSystemDTO) throws IllegalArgumentException {
        Rule activeRule = null;
        for (Rule rule : parkingSystemDTO.getRules()) {
            activeRule = rule;
        }
        if (activeRule == null) {
            throw new IllegalArgumentException("No rule here");
        }
        LocalTime startTime = activeRule.getStartTime();
        LocalTime endTime = activeRule.getEndTime();
        double hoursAvailable = Duration.between(startTime, endTime).toHours();
        return activeRule.getCost() * hoursAvailable;
    }


    public String getExitTime(double money, ParkingSystemDTO parkingSystemDTO, TransactionPaymentType transactionPaymentType) throws IllegalArgumentException {
        Rule activeRule = null;
        for (Rule rule : parkingSystemDTO.getRules()) {
            activeRule = rule;
        }
        if (activeRule == null) {
            return null;
        }
        if (transactionPaymentType.equals(CASH)) {
            LocalDateTime now = LocalDateTime.now();
//        LocalDateTime now = LocalDateTime.of(2023, 03, 29, 01, 0, 0); USE THIS AS OPTIONAL DATE TIME
            double dailyCost = calculateDailyCost(parkingSystemDTO);
            int daysToAdd = (int) (money / dailyCost);
            double secondsRemaining = (money % dailyCost) * 3600;
            LocalDateTime exitTime = now.plusDays(daysToAdd).plusSeconds((long) secondsRemaining);

            if (now.toLocalTime().isAfter(activeRule.getEndTime()) && now.toLocalTime().isBefore(LocalTime.MAX)) {
                Duration durationUntilExitTime = Duration.between(now.toLocalTime(), exitTime.toLocalTime());
                exitTime = activeRule.getStartTime().atDate(exitTime.toLocalDate()).plusDays(1).plus(durationUntilExitTime);
            }
            if (now.toLocalTime().isBefore(activeRule.getEndTime()) && exitTime.toLocalTime().isAfter(activeRule.getEndTime())) {
                Duration duration = Duration.between(activeRule.getEndTime(), exitTime.toLocalTime());
                exitTime = activeRule.getStartTime().atDate(exitTime.toLocalDate()).plusDays(1).plus(duration);
            }

            if (now.toLocalTime().isBefore(LocalTime.MAX) && now.toLocalTime().isAfter(activeRule.getStartTime()) && exitTime.toLocalTime().isBefore(activeRule.getStartTime()) && exitTime.toLocalTime().isAfter(LocalTime.MIDNIGHT)) {
                Duration durationAfterMid = Duration.between(LocalTime.MIDNIGHT, exitTime.toLocalTime());
                Duration durationUntilMid = Duration.between(activeRule.getEndTime(), LocalTime.MIDNIGHT);
                exitTime = activeRule.getStartTime().atDate(exitTime.toLocalDate()).plusDays(1).plus(durationAfterMid).plus(durationUntilMid);
            }

            if (now.toLocalTime().isAfter(LocalTime.MIDNIGHT) && now.toLocalTime().isBefore(activeRule.getStartTime())) {
                Duration duration = Duration.between(now.toLocalTime(), exitTime.toLocalTime());
                exitTime = activeRule.getStartTime().atDate(exitTime.toLocalDate()).plus(duration);
            }

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            return "You can park until: " + exitTime.toLocalTime().format(formatter) + " of Date: " + exitTime.toLocalDate();
        } else return "This payment type is not supported yet";
    }
}


