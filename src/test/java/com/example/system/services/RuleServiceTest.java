package com.example.system.services;

import com.example.system.dto.ParkingSystemDTO;
import com.example.system.entities.Rule;
import com.example.system.entities.TransactionPaymentType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class RuleServiceTest {


    @Test
    public void testCalculateDailyCost() {

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
}
