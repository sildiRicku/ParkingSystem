package com.example.system.serviceimplementations;

import com.example.system.helperclasses.MutableDouble;
import com.example.system.models.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ParkingSystemServiceImplTest {

    private ParkingSystemServiceImpl parkingSystem;

    @BeforeEach
    void setUp() {
        Validations validations = new Validations();
        TimeUtils timeUtils=new TimeUtils();
        Calculations calculations = new Calculations(timeUtils);
        parkingSystem = new ParkingSystemServiceImpl(calculations, validations);
    }


    @Test
    void testCalculateExitTime() {
        List<Rule> rules = new ArrayList<>();
        rules.add(new Rule(10, LocalTime.of(8, 0), LocalTime.of(12, 0)));
        rules.add(new Rule(20, LocalTime.of(12, 0), LocalTime.of(18, 0)));

        LocalDateTime now = LocalDateTime.of(2023, 6, 9, 10, 0);  // 2023-06-09 10:00
        MutableDouble money = new MutableDouble(50);

        LocalDateTime exitTime = parkingSystem.calculateExitTime(now, money, rules);

        LocalDateTime expectedExitTime = LocalDateTime.of(2023, 6, 9, 13, 30);  // 2023-06-09 12:00

        assertEquals(expectedExitTime, exitTime);
    }
}