package com.example.system.serviceimplementations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
class ValidationsTest {
    private Validations validations;

    @BeforeEach
    public void setUp() {
        validations = new Validations();
    }

    @Test
    void isBetween_ReturnsTrueWhenMyTimeIsBetweenStartAndEnd() {
        LocalTime start = LocalTime.of(9, 0);
        LocalTime end = LocalTime.of(18, 0);
        LocalTime myTime = LocalTime.of(12, 0);

        assertTrue(validations.isBetween(myTime, start, end));
    }

    @Test
    void sameRangeTimes_ReturnsTrueWhenBothTimesAreInRange() {
        LocalTime start = LocalTime.of(9, 0);
        LocalTime end = LocalTime.of(18, 0);
        LocalTime t1 = LocalTime.of(12, 0);
        LocalTime t2 = LocalTime.of(15, 0);

        assertTrue(validations.sameRangeTimes(t1, t2, start, end));
    }
}