package com.example.system.services;

import com.example.system.dto.ParkingSystemDTO;
import com.example.system.dto.RuleDTO;

import com.example.system.models.Rule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class RuleService {
    private final ParkingSystemService parkingSystemService;

    @Autowired
    public RuleService(ParkingSystemService parkingSystemService) {
        this.parkingSystemService = parkingSystemService;
    }

    //    public boolean checkRuleConflict(List<Rule> rules) {
//        int numRules = rules.size();
//
//        for (int i = 0; i < numRules; i++) {
//            Rule rule1 = rules.get(i);
//
//            for (int j = i + 1; j < numRules; j++) {
//                Rule rule2 = rules.get(j);
//
//                // Check if the time ranges overlap
//                if (rule1.getStartTime().isBefore(rule2.getEndTime()) &&
//                        rule2.getStartTime().isBefore(rule1.getEndTime())) {
//                    return true; // Overlapping rules found
//                }
//            }
//        }
//        return false; // No overlapping rules found
//    }
    public boolean checkRuleConflict(List<Rule> rules) {
        int numRules = rules.size();

        for (int i = 0; i < numRules; i++) {
            Rule rule1 = rules.get(i);

            for (int j = i + 1; j < numRules; j++) {
                Rule rule2 = rules.get(j);

                // Check if the time ranges overlap
                if (doTimeRangesOverlap(rule1.getStartTime(), rule1.getEndTime(),
                        rule2.getStartTime(), rule2.getEndTime())) {
                    return true; // Overlapping rules found
                }
            }
        }
        return false; // No overlapping rules found
    }

    // Custom method to check if two time ranges overlap
    private boolean doTimeRangesOverlap(LocalTime startTime1, LocalTime endTime1,
                                        LocalTime startTime2, LocalTime endTime2) {
        if (startTime1.isBefore(endTime1) && startTime2.isBefore(endTime2)) {
            // Both rules do not pass midnight
            return endTime1.isAfter(startTime2) && endTime2.isAfter(startTime1);
        } else {
            // At least one rule passes midnight
            return startTime1.isBefore(endTime2) || startTime2.isBefore(endTime1);
        }
    }

}

