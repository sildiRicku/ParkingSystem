package com.example.system.serviceimplementations;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TimeUtilsTest {

    private TimeUtils timeUtils;

    @Before
    public void setUp() {
        timeUtils = new TimeUtils();
    }

    @Test
    public void testRoundToMinutes() {
        LocalDateTime time = LocalDateTime.of(2023, 6, 9, 23, 59, 30);
        LocalDateTime expected = LocalDateTime.of(2023, 6, 10, 0, 0, 0);

        LocalDateTime result = timeUtils.roundToMinutes(time);

        assertEquals(expected, result);
    }


}
