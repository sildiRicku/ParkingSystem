package com.example.system.serviceimplementations;

import com.example.system.entities.Rule;
import com.example.system.helperclasses.MutableDouble;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class ParkingSystemServiceImpl {

    Calculations rulecalculations = new Calculations();
    Validations validations=new Validations();

    public LocalDateTime calculateExitTime(LocalDateTime now, MutableDouble money, List<Rule> rules) {
        List<Rule> newRules = new ArrayList<>();
        for (Rule rule : rules) {
            if (!(validations.sameRangeTimes(rule.getStartTime(), rule.getEndTime(), LocalTime.MIN, LocalTime.NOON) || validations.sameRangeTimes(rule.getStartTime(), rule.getEndTime(), LocalTime.NOON, LocalTime.MAX))) {

                if (validations.isBetween(rule.getStartTime(), LocalTime.MIN, LocalTime.NOON)) {
                    newRules.add(new Rule(rule.getCost(), rule.getStartTime(), LocalTime.NOON));
                } else {
                    newRules.add(new Rule(rule.getCost(), rule.getStartTime(), LocalTime.MAX));
                }

                if (validations.isBetween(rule.getEndTime(), LocalTime.MIN, LocalTime.NOON)) {
                    newRules.add(new Rule(rule.getCost(), LocalTime.MIN, rule.getEndTime()));
                } else {
                    newRules.add(new Rule(rule.getCost(), LocalTime.NOON, rule.getEndTime()));
                }

            } else {
                newRules.add(rule);
            }
        }

        boolean remainMoney = true;
        while (remainMoney) {
            for (Rule rule : newRules) {
                if (!((now.toLocalTime().equals(rule.getStartTime()) || now.toLocalTime().isAfter(rule.getStartTime())) && (now.toLocalTime().equals(rule.getEndTime()) || now.toLocalTime().isBefore(rule.getEndTime())))) {
                    continue;
                }
                if (money.getValue() == 0 && rule.getCost() != 0) {
                    remainMoney = false;
                    break;
                }
                now = rulecalculations.calculateRemainTimePerRule(now, rule, money);

            }
        }

        return now;
    }


}
