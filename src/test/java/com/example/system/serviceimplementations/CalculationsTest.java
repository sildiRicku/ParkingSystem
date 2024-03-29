package com.example.system.serviceimplementations;

import com.example.system.helperclasses.MutableDouble;
import com.example.system.models.Rule;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class CalculationsTest {

    private Calculations calculations;
    private TimeUtils timeUtils;

    @BeforeEach
    public void setUp() {
        timeUtils = mock(TimeUtils.class);
        calculations = new Calculations();
    }

    @Test
    void calculateRemainTimePerRule_ReturnsRoundedTimeWhenRuleCostIsZero() {
        LocalDateTime now = LocalDateTime.of(2023, 6, 9, 12, 0, 0);
        Rule rule = new Rule();
        rule.setCost(0);
        rule.setEndTime(LocalTime.of(13, 0, 0));
        MutableDouble remainMoney = new MutableDouble(1);

        LocalDateTime expected = LocalDateTime.of(2023, 6, 9, 13, 0, 0);
        when(timeUtils.roundToMinutes(now.plusNanos(Duration.between(now.toLocalTime(), rule.getEndTime()).toNanos())))
                .thenReturn(expected);

        LocalDateTime result = calculations.calculateRemainTimePerRule(now, rule, remainMoney);

        assertEquals(expected, result);
    }
}