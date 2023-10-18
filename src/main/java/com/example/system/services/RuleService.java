package com.example.system.services;

import com.example.system.models.Rule;
import com.example.system.serviceimplementations.TimeUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RuleService {
    private TimeUtils timeUtils;

    public RuleService(TimeUtils timeUtils) {
        this.timeUtils = timeUtils;
    }


    public boolean checkRuleConflict(List<Rule> rules) {
        int numRules = rules.size();

        for (int i = 0; i < numRules; i++) {
            Rule rule1 = rules.get(i);

            for (int j = i + 1; j < numRules; j++) {
                Rule rule2 = rules.get(j);

                if (timeUtils.timeRangesOverlap(rule1.getStartTime(), rule1.getEndTime(),
                        rule2.getStartTime(), rule2.getEndTime())) {
                    return true;
                }
            }
        }
        return false;
    }


}

