package com.example.system.services;

import com.example.system.entities.Rule;
import com.example.system.helperclasses.MutableDouble;
import com.example.system.repositories.ParkingSystemRepo;
import com.example.system.serviceimplementations.ParkingSystemServiceImpl;
import com.example.system.serviceimplementations.Calculations;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ParkingSystemServiceTest {
    @InjectMocks
    private ParkingSystemService parkingSystemService;
    @Mock
    private ParkingSystemRepo parkingSystemRepo;
    @Mock
    private ModelMapper modelMapper;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCalulateRemainTimePerRule() {
        Rule rule = mock(Rule.class);
        MutableDouble remainMoney = mock(MutableDouble.class);
        Calculations calculations = new Calculations();


        // Set up the mock objects
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime endTime = now.plusHours(1);
        when(rule.getCost()).thenReturn(10.0);
        when(rule.getEndTime()).thenReturn(endTime.toLocalTime());
        when(remainMoney.getValue()).thenReturn(20.0);

        LocalDateTime result = calculations.calculateRemainTimePerRule(now, rule, remainMoney);

        assertEquals(now.plusHours(1).withSecond(0).withNano(0), result);

        ArgumentCaptor<Double> argumentCaptor = ArgumentCaptor.forClass(Double.class);
        verify(remainMoney).setValue(argumentCaptor.capture());
        assertEquals(10.0, argumentCaptor.getValue());
    }


    @Test
    void calExitTime_shouldReturnExpectedExitTime() {
        LocalDateTime now = LocalDateTime.of(2023, 5, 19, 10, 0); // Set your test date and time
        MutableDouble money = new MutableDouble(100.0); // Set initial money value
        List<Rule> rules = new ArrayList<>();
        Rule r1 = new Rule();
        r1.setCost(1);
        r1.setDetails("R1");
        r1.setName("R1");
        r1.setStartTime(LocalTime.of(8, 0));
        r1.setEndTime(LocalTime.of(20, 0));

        Rule r2 = new Rule();
        r2.setCost(0.0);
        r2.setDetails("R1");
        r2.setName("R1");
        r2.setStartTime(LocalTime.of(20, 0));
        r2.setEndTime(LocalTime.of(8, 0));
        rules.add(r2);
        rules.add(r1);
        ParkingSystemServiceImpl implementation = new ParkingSystemServiceImpl();

        LocalDateTime exitTime = implementation.calculateExitTime(now, money, rules);


        assertEquals(LocalDateTime.of(2023, 05, 27, 14, 0), exitTime);
    }

    @Test
    void calExitTime_shouldExitImmediatelyIfNoMoneyLeft() {
        LocalDateTime now = LocalDateTime.of(2023, 5, 19, 10, 0);
        MutableDouble money = new MutableDouble(0.0); // No money left
        List<Rule> rules = new ArrayList<>();
        Rule r1 = new Rule();
        r1.setCost(1);
        r1.setDetails("R1");
        r1.setName("R1");
        r1.setStartTime(LocalTime.of(8, 0));
        r1.setEndTime(LocalTime.of(20, 0));

        Rule r2 = new Rule();
        r2.setCost(0.0);
        r2.setDetails("R1");
        r2.setName("R1");
        r2.setStartTime(LocalTime.of(20, 0));
        r2.setEndTime(LocalTime.of(8, 0));
        rules.add(r2);
        rules.add(r1);
        ParkingSystemServiceImpl implementation = new ParkingSystemServiceImpl();

        LocalDateTime exitTime = implementation.calculateExitTime(now, money, rules);

        assertEquals(now, exitTime);
    }
}


