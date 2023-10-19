package com.example.system.serviceimplementations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
class TimeUtilsTest {

    private TimeUtils timeUtils;

    @BeforeEach
    public void setUp() {
        timeUtils = new TimeUtils();
    }

    @Test
    void roundToMinutes() {
        LocalDateTime time = LocalDateTime.of(2023, 6, 9, 23, 59, 30);
        LocalDateTime expected = LocalDateTime.of(2023, 6, 10, 0, 0, 0);

        LocalDateTime result = timeUtils.roundToMinutes(time);

        assertEquals(expected, result);
    }

    @Test
    void timeRangesOverlap() {
        // Test case 1: Overlapping ranges
        LocalTime startTime1 = LocalTime.of(9, 0);
        LocalTime endTime1 = LocalTime.of(12, 0);
        LocalTime startTime2 = LocalTime.of(11, 0);
        LocalTime endTime2 = LocalTime.of(14, 0);

        assertTrue(timeUtils.timeRangesOverlap(startTime1, endTime1, startTime2, endTime2));

        // Test case 2: Non-overlapping ranges
        LocalTime startTime3 = LocalTime.of(9, 0);
        LocalTime endTime3 = LocalTime.of(10, 0);
        LocalTime startTime4 = LocalTime.of(11, 0);
        LocalTime endTime4 = LocalTime.of(12, 0);

        assertFalse(timeUtils.timeRangesOverlap(startTime3, endTime3, startTime4, endTime4));
    }
}
