package com.example.system.serviceimplementations;

import com.example.system.interfaces.IParkingSystemService;
import com.example.system.models.Rule;
import com.example.system.helperclasses.MutableDouble;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
@AllArgsConstructor
@Component
public class ParkingSystemServiceImpl implements IParkingSystemService {
    private final Calculations ruleCalculations=new Calculations();
    private final Validations validations=new Validations();



    @Override
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
                now = ruleCalculations.calculateRemainTimePerRule(now, rule, money);

            }
        }

        return now;
    }


}
