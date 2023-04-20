package com.example.system.services;

import com.example.system.dto.ParkingSystemDTO;
import com.example.system.entities.Rule;
import com.example.system.repositories.RuleRepo;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


class RuleServiceTest {


    @Test
    void testCalculateDailyCost() {
        RuleRepo ruleRepo = Mockito.mock(RuleRepo.class);
        ModelMapper modelMapper = Mockito.mock(ModelMapper.class);
        RuleService ruleService = new RuleService(ruleRepo, modelMapper);

        ParkingSystemDTO parkingSystemDTO = Mockito.mock(ParkingSystemDTO.class);

        Rule rule = mock(Rule.class);
        List<Rule> rules = new ArrayList<>();
        rules.add(rule);
        when(parkingSystemDTO.getRules()).thenReturn(rules);


        when(rule.getStartTime()).thenReturn(LocalTime.of(9, 0));
        when(rule.getEndTime()).thenReturn(LocalTime.of(18, 0));
        when(rule.getCost()).thenReturn(10.0);

        double dailyCost = ruleService.calculateDailyCost(rule);
        assertEquals(90.0, dailyCost);
    }

    @Test
    void calculateExitTime() {
        LocalDateTime now = LocalDateTime.of(2022, 3, 17, 10, 00, 0);
        Rule activeRule = mock(Rule.class);
        when(activeRule.getStartTime()).thenReturn(LocalTime.of(9, 0, 0));
        when(activeRule.getEndTime()).thenReturn(LocalTime.of(18, 0, 0));
        int daysToAdd = 2;
        double secondsRemaining = 3600.0;

        RuleRepo ruleRepo = mock(RuleRepo.class);
        ModelMapper modelMapper = mock(ModelMapper.class);

        RuleService ruleService = new RuleService(ruleRepo, modelMapper);

        LocalDateTime result = ruleService.calculateExitTime(now, activeRule, daysToAdd, secondsRemaining);
        LocalDateTime expected = LocalDateTime.of(2022, 3, 19, 11, 0, 0);
        assertEquals(expected,result);
    }


    @Test
    void getExitTime() {
        LocalDateTime now = LocalDateTime.of(2022, 4, 19, 12, 0, 0);

        ParkingSystemDTO parkingSystemDTO = mock(ParkingSystemDTO.class);
        List<Rule> rules = new ArrayList<>();
        Rule rule = new Rule();
        rule.setStartTime(LocalTime.of(9, 0, 0));
        rule.setEndTime(LocalTime.of(18, 0, 0));
        rule.setCost(10.0);
        rules.add(rule);
        when(parkingSystemDTO.getRules()).thenReturn(rules);
        RuleService ruleService = mock(RuleService.class);
        when(ruleService.calculateDailyCost(rule)).thenReturn(80.0);
        LocalDateTime expectedExitTime = LocalDateTime.of(2022, 4, 20, 15, 0, 0);
        when(ruleService.calculateExitTime(now, rule, 0, 0.0)).thenReturn(expectedExitTime);

        LocalDateTime actualExitTime = ruleService.calculateExitTime(now, rule, 0, 0.0);
        assertEquals(expectedExitTime, actualExitTime);
    }

}