package com.example.system.services;

import com.example.system.dto.ParkingSystemDTO;
import com.example.system.dto.RuleDTO;
import com.example.system.entities.TransactionPaymentType;
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

import static com.example.system.entities.TransactionPaymentType.CASH;

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

    public String getHoursForMoney(double money, ParkingSystemDTO parkingSystemDTO, TransactionPaymentType transactionPaymentType) {
        Rule activeRule = null;
        for (Rule rule : parkingSystemDTO.getRules()) {
            activeRule = rule;
        }
        if (activeRule == null) {
            return null;
        }
        if (transactionPaymentType == CASH) {
            LocalTime now = LocalTime.now();
            LocalTime startTime = activeRule.getStartTime();
            LocalTime endTime = activeRule.getEndTime();
            LocalTime midnight = LocalTime.of(0, 0, 0);

            double costPerHour = activeRule.getCost();
            double hours = money / costPerHour;
            long seconds = (long) (hours * 3600);
            LocalTime exitTime = now.plusSeconds(seconds);

            if (exitTime.isAfter(midnight) && exitTime.isBefore(startTime)) {
                Duration durationAfterMid = Duration.between(midnight, exitTime);
                Duration durationUntilMid = Duration.between(endTime, LocalTime.MAX);
                long difference = durationAfterMid.getSeconds();
                long differenceUntilMid = durationUntilMid.getSeconds();
                exitTime = startTime.plusSeconds(difference).plusSeconds(differenceUntilMid);
            }
            if (exitTime.isAfter(endTime)) {
                Duration duration = Duration.between(endTime, exitTime);
                long difference = duration.getSeconds();
                exitTime = startTime.plusSeconds(difference);
            }

            LocalDateTime exitDateTime = LocalDateTime.now();
            long secondsLeftInDay = Duration.between(exitDateTime.toLocalTime(), LocalTime.MAX).getSeconds();
            long secondsLeft = seconds % (24 * 3600); //seconds that do not complete a day
            long daysToAdd = (seconds - secondsLeft + secondsLeftInDay) / (24 * 3600);

            exitDateTime = exitDateTime.plusDays(daysToAdd);
            if (exitTime.isBefore(now)) {
                exitDateTime = exitDateTime.plusDays(1);
            }
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            return "You can park until " + exitTime.format(formatter) + " of Date: " + exitDateTime.toLocalDate();

        } else {
            return "This payment type is not available";
        }
    }


}
