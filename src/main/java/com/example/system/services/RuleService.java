package com.example.system.services;

import com.example.system.classes.ParkingResponse;
import com.example.system.dto.ParkingSystemDTO;
import com.example.system.dto.RuleDTO;
import com.example.system.entities.Rule;
import com.example.system.entities.TransactionPaymentType;
import com.example.system.exceptionhandlers.InvalidArgument;
import com.example.system.exceptionhandlers.NotFoundException;
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
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
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

    public double calculateDailyCost(List<Rule> activeRules) {
        double cost = 0;
        for (Rule rule : activeRules) {
            LocalTime startTime = rule.getStartTime();
            LocalTime endTime = rule.getEndTime();
            double hoursAvailable = Math.abs(Duration.between(startTime, endTime).toHours());
            double ruleCost = hoursAvailable * rule.getCost();
            cost += ruleCost;
        }
        return cost;
    }


    public LocalDateTime calculateExitTime(LocalDateTime now, List<Rule> activeRules, int daysToAdd, double secondsRemaining) {

        LocalDateTime exitTime = now.plusDays(daysToAdd).plusSeconds((long) secondsRemaining);
        LocalTime ruleStartTime = activeRules.getStartTime();
        LocalTime ruleEndTime = activeRules.getEndTime();

        if (now.toLocalTime().isAfter(ruleEndTime)) {
            Duration durationUntilExitTime = Duration.between(now.toLocalTime(), exitTime.toLocalTime());
            exitTime = ruleStartTime.atDate(exitTime.toLocalDate()).plusDays(1).plus(durationUntilExitTime);
        } else if (now.toLocalTime().isBefore(ruleStartTime)) {
            Duration duration = Duration.between(now.toLocalTime(), exitTime.toLocalTime());
            exitTime = ruleStartTime.atDate(exitTime.toLocalDate()).plus(duration);
        } else if (now.toLocalTime().isBefore(ruleEndTime) && exitTime.toLocalTime().isAfter(ruleEndTime)) {
            Duration duration = Duration.between(ruleEndTime, exitTime.toLocalTime());
            exitTime = ruleStartTime.atDate(exitTime.toLocalDate()).plusDays(1).plus(duration);
        } else if (now.toLocalTime().isAfter(ruleStartTime) && exitTime.toLocalTime().isBefore(ruleStartTime)) {
            Duration durationAfterMid = Duration.between(LocalTime.MIDNIGHT, exitTime.toLocalTime());
            Duration durationUntilMid = Duration.between(ruleEndTime, LocalTime.MIDNIGHT);
            exitTime = ruleStartTime.atDate(exitTime.toLocalDate()).plusDays(1).plus(durationAfterMid).plus(durationUntilMid);
        }
        return exitTime;
    }

    public ParkingResponse getExitTime(LocalDateTime now, double money, String plateNumber, ParkingSystemDTO parkingSystemDTO, TransactionPaymentType transactionPaymentType) {
        List<Rule> activeRules = parkingSystemDTO.getRules();

        if (activeRules == null) {
            throw new NotFoundException("This parking system does not have any rule applied ");
        }

        double dailyCost = calculateDailyCost(activeRules);
        int daysToAdd = (int) (money / dailyCost);
        double secondsRemaining = (money % dailyCost) * 3600;
        LocalDateTime exitTime = calculateExitTime(now, activeRules, daysToAdd, secondsRemaining);
        if (transactionPaymentType.equals(CASH)) {
            return new ParkingResponse(plateNumber, exitTime.format(formatter));
        } else {
            throw new InvalidArgument("Sorry, this payment type is not available yet");

        }
    }
}