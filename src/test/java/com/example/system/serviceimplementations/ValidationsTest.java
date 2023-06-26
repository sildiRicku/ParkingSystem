package com.example.system.serviceimplementations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class ValidationsTest {
    private Validations validations;

    @BeforeEach
    public void setUp() {
        validations = new Validations();
    }

    @Test
    public void testIsBetween_ReturnsTrueWhenMyTimeIsBetweenStartAndEnd() {
        LocalTime start = LocalTime.of(9, 0);
        LocalTime end = LocalTime.of(18, 0);
        LocalTime myTime = LocalTime.of(12, 0);

        assertTrue(validations.isBetween(myTime, start, end));
    }

    @Test
    public void testSameRangeTimes_ReturnsTrueWhenBothTimesAreInRange() {
        LocalTime start = LocalTime.of(9, 0);
        LocalTime end = LocalTime.of(18, 0);
        LocalTime t1 = LocalTime.of(12, 0);
        LocalTime t2 = LocalTime.of(15, 0);

        assertTrue(validations.sameRangeTimes(t1, t2, start, end));
    }
}