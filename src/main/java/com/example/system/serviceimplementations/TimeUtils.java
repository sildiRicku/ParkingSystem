package com.example.system.serviceimplementations;


import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Component
public class TimeUtils {
    LocalDateTime roundToMinutes(LocalDateTime time) {
        if (time.getSecond() >= 30 && time.getMinute() == 59) {
            time = time.plusMinutes(1);
        }
        return time.withSecond(0).withNano(0);
    }

    public boolean timeRangesOverlap(LocalTime startTime1, LocalTime endTime1,
                                     LocalTime startTime2, LocalTime endTime2) {
        if (startTime1.isBefore(endTime1) && startTime2.isBefore(endTime2)) {
            return endTime1.isAfter(startTime2) && endTime2.isAfter(startTime1);
        } else {
            return startTime1.isBefore(endTime2) || startTime2.isBefore(endTime1);
        }
    }

}
