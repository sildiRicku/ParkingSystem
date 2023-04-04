package com.example.system.services;

import com.example.system.dto.ParkingSystemDTO;
import com.example.system.entities.Rule;
import com.example.system.entities.TransactionPaymentType;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


class RuleServiceTest {


    @Test
    void testCalculateDailyCost() {

        RuleService ruleService = mock(RuleService.class);

        ParkingSystemDTO parkingSystemDTO = mock(ParkingSystemDTO.class);

        Rule rule = mock(Rule.class);


        when(rule.getStartTime()).thenReturn(LocalTime.of(9, 0));
        when(rule.getEndTime()).thenReturn(LocalTime.of(18, 0));
        when(rule.getCost()).thenReturn(10.0);

        when(ruleService.calculateDailyCost(parkingSystemDTO)).thenReturn(90.0);

        double dailyCost = ruleService.calculateDailyCost(parkingSystemDTO);
        assertEquals(90.0, dailyCost);

    }

    @Test
    void getExitTime() {

        RuleService ruleService = mock(RuleService.class);
        ParkingSystemDTO parkingSystemDTO = mock(ParkingSystemDTO.class);
        Rule rule = mock(Rule.class);

        List<Rule> rules = new ArrayList<>();
        rules.add(rule);
        when(parkingSystemDTO.getRules()).thenReturn(rules);


        when(rule.getStartTime()).thenReturn(LocalTime.of(8, 0));
        when(rule.getEndTime()).thenReturn(LocalTime.of(20, 0));
        when(rule.getCost()).thenReturn(1.0);

        when(ruleService.calculateDailyCost(parkingSystemDTO)).thenReturn(12.0);
        String exitTime = ruleService.getExitTime(12.0, parkingSystemDTO, TransactionPaymentType.CASH);

        assertEquals("You can park until: 08:00:00 of Date: 2023-04-04", exitTime);
    }

}
