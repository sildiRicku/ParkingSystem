package com.example.system.services;

import com.example.system.models.Rule;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;

@Service
public class RuleService {


    public boolean checkRuleConflict(List<Rule> rules) {
        int numRules = rules.size();

        for (int i = 0; i < numRules; i++) {
            Rule rule1 = rules.get(i);

            for (int j = i + 1; j < numRules; j++) {
                Rule rule2 = rules.get(j);

                if (doTimeRangesOverlap(rule1.getStartTime(), rule1.getEndTime(),
                        rule2.getStartTime(), rule2.getEndTime())) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean doTimeRangesOverlap(LocalTime startTime1, LocalTime endTime1,
                                        LocalTime startTime2, LocalTime endTime2) {
        if (startTime1.isBefore(endTime1) && startTime2.isBefore(endTime2)) {
            return endTime1.isAfter(startTime2) && endTime2.isAfter(startTime1);
        } else {
            return startTime1.isBefore(endTime2) || startTime2.isBefore(endTime1);
        }
    }


}

