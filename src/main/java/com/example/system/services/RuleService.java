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

    public double calculateDailyCost(Rule activeRule) {
        LocalTime startTime = activeRule.getStartTime();
        LocalTime endTime = activeRule.getEndTime();
        double hoursAvailable = Duration.between(startTime, endTime).toHours();
        return activeRule.getCost() * hoursAvailable;
    }

    public int calculateDaysToAdd(double money, double dailyCost) {
        return (int) (money / dailyCost);
    }

    public LocalDateTime calculateExitTime(LocalDateTime now, Rule activeRule, int daysToAdd, double secondsRemaining) {
        LocalDateTime exitTime = now.plusDays(daysToAdd).plusSeconds((long) secondsRemaining);
        LocalTime ruleStartTime = activeRule.getStartTime();
        LocalTime ruleEndTime = activeRule.getEndTime();

        if (now.toLocalTime().isAfter(ruleEndTime) && now.toLocalTime().isBefore(LocalTime.MAX)) {
            Duration durationUntilExitTime = Duration.between(now.toLocalTime(), exitTime.toLocalTime());
            exitTime = ruleStartTime.atDate(exitTime.toLocalDate()).plusDays(1).plus(durationUntilExitTime);
        }
        if (now.toLocalTime().isBefore(ruleEndTime) && exitTime.toLocalTime().isAfter(ruleEndTime)) {
            Duration duration = Duration.between(activeRule.getEndTime(), exitTime.toLocalTime());
            exitTime = ruleStartTime.atDate(exitTime.toLocalDate()).plusDays(1).plus(duration);
        }

        if (now.toLocalTime().isBefore(LocalTime.MAX) && now.toLocalTime().isAfter(ruleStartTime) && exitTime.toLocalTime().isBefore(ruleStartTime) && exitTime.toLocalTime().isAfter(LocalTime.MIDNIGHT)) {
            Duration durationAfterMid = Duration.between(LocalTime.MIDNIGHT, exitTime.toLocalTime());
            Duration durationUntilMid = Duration.between(ruleEndTime, LocalTime.MIDNIGHT);
            exitTime = ruleStartTime.atDate(exitTime.toLocalDate()).plusDays(1).plus(durationAfterMid).plus(durationUntilMid);
        }

        if (now.toLocalTime().isAfter(LocalTime.MIDNIGHT) && now.toLocalTime().isBefore(ruleStartTime)) {
            Duration duration = Duration.between(now.toLocalTime(), exitTime.toLocalTime());
            exitTime = ruleStartTime.atDate(exitTime.toLocalDate()).plus(duration);
        }
        return exitTime;
    }

    public String getExitTime(LocalDateTime now, double money, String plateNumber, ParkingSystemDTO parkingSystemDTO, TransactionPaymentType transactionPaymentType) {
        Rule activeRule = parkingSystemDTO.getRules().stream().findFirst().orElse(null);

        if (activeRule == null) {
            return "Error";
        }

        double dailyCost = calculateDailyCost(activeRule);
        int daysToAdd = calculateDaysToAdd(money, dailyCost);
        double secondsRemaining = (money % dailyCost) * 3600;
        LocalDateTime exitTime = calculateExitTime(now, activeRule, daysToAdd, secondsRemaining);

        if (transactionPaymentType.equals(CASH)) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            return "Vehicle with plate number: " + plateNumber + " can park until: " + exitTime.toLocalTime().format(formatter) + " of Date: " + exitTime.toLocalDate();
        } else {
            return "This payment type is not supported yet";
        }
    }
}