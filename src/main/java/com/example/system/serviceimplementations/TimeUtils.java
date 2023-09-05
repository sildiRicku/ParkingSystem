package com.example.system.serviceimplementations;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
public class TimeUtils {
    LocalDateTime roundToMinutes(LocalDateTime time) {
        if (time.getSecond() >= 30 && time.getMinute() == 59) {
            time = time.plusMinutes(1);
        }
        return time.withSecond(0).withNano(0);
    }
}
