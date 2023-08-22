package com.example.system.serviceimplementations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;

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
}