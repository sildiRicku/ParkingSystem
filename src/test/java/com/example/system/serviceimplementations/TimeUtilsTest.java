package com.example.system.serviceimplementations;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class TimeUtilsTest {

    private TimeUtils timeUtils;

    @BeforeEach
    public void setUp() {
        timeUtils = new TimeUtils();
    }

    @Test
     void testRoundToMinutes() {
        LocalDateTime time = LocalDateTime.of(2023, 6, 9, 23, 59, 30);
        LocalDateTime expected = LocalDateTime.of(2023, 6, 10, 0, 0, 0);

        LocalDateTime result = timeUtils.roundToMinutes(time);

        assertEquals(expected, result);
    }
}