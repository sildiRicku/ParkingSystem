package com.example.system.serviceimplementations;

import com.example.system.entities.Rule;
import com.example.system.helperclasses.MutableDouble;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class RuleCalculations {
    TimeUtils timeUtils = new TimeUtils();

    public LocalDateTime calculateRemainTimePerRule(LocalDateTime now, Rule rule, MutableDouble remainMoney) {

        if (rule.getCost() == 0) {
            double dif = Duration.between(now.toLocalTime(), rule.getEndTime()).toNanos();
            return timeUtils.roundToMinutes(now.plusNanos((long) dif));
        } else {
            double nanoDiff = Duration.between(now.toLocalTime(), rule.getEndTime()).toNanos();
            remainMoney.setValue(remainMoney.getValue() - (nanoDiff / 3600000000000.0) * rule.getCost());
            if (remainMoney.getValue() < 0) {
                long nanoToRemove = (long) ((remainMoney.getValue() * 3600000000000.0) / rule.getCost());
                remainMoney.setValue(0.0);
                now = now.plusNanos((long) nanoDiff + nanoToRemove);
                if (now.toLocalTime().equals(LocalTime.MAX)) {
                    now = LocalDateTime.of(now.toLocalDate(), LocalTime.MIN);
                }
                return timeUtils.roundToMinutes(now);
            } else {
                now = now.plusNanos((long) nanoDiff);
                if (now.toLocalTime().equals(LocalTime.MAX)) {
                    now = LocalDateTime.of(now.toLocalDate(), LocalTime.MIN);
                }
                return timeUtils.roundToMinutes(now);
            }
        }

    }


}
