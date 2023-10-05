package com.example.system.services;

import com.example.system.dto.ParkingSystemDTO;
import com.example.system.dto.RuleDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;

@Service
public class RuleService {
    private final ParkingSystemService parkingSystemService;

    @Autowired
    public RuleService(ParkingSystemService parkingSystemService) {
        this.parkingSystemService = parkingSystemService;
    }

    public boolean checkRuleConflict(ParkingSystemDTO parkingSystem) {
        List<RuleDTO> rules = parkingSystemService.getRulesForParkingSystem(parkingSystem.getId());
        for (int i = 0; i < rules.size(); i++) {
            for (int j = i + 1; j < rules.size(); j++) {
                RuleDTO rule1 = rules.get(i);
                RuleDTO rule2 = rules.get(j);

                if (isTimeRangeOverlap(rule1.getStartTime(), rule1.getEndTime(), rule2.getStartTime(), rule2.getEndTime())) {
                    return true;
                }
            }
        }
        return false;
    }

    //    private boolean isTimeRangeOverlap(LocalTime start1, LocalTime end1, LocalTime start2, LocalTime end2) {
//        return !end1.isBefore(start2) && !end2.isBefore(start1);
//        //handle the endTime=startTime of next case
//    }
    private boolean isTimeRangeOverlap(LocalTime start1, LocalTime end1, LocalTime start2, LocalTime end2) {
        return !end1.isBefore(start2) && !end2.isBefore(start1) && !end1.equals(start2) && !end2.equals(start1);
    }


}

