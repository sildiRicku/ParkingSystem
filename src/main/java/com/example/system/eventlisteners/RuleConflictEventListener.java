package com.example.system.eventlisteners;

import com.example.system.dto.ParkingSystemDTO;
import com.example.system.models.Rule;
import com.example.system.services.ParkingSystemService;
import com.example.system.services.RuleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RuleConflictEventListener implements ApplicationListener<ApplicationEvent> {

    private static final Logger logger = LoggerFactory.getLogger(RuleConflictEventListener.class);
    private final ParkingSystemService parkingSystemService;
    private final RuleService ruleService;

    @Autowired
    public RuleConflictEventListener(ParkingSystemService parkingSystemService, RuleService ruleService) {
        this.parkingSystemService = parkingSystemService;
        this.ruleService = ruleService;

    }

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof ContextRefreshedEvent) {
            List<ParkingSystemDTO> parkingSystemDTOS = parkingSystemService.getAllParkingSystems();
            for (ParkingSystemDTO parkingSystemDTO : parkingSystemDTOS) {
                List<Rule> rules = parkingSystemDTO.getRules();

                boolean hasConflicts = ruleService.checkRuleConflict(rules);
                if (hasConflicts) {
                    logger.error("*****Rule conflicts detected for parking system with id : {} ", parkingSystemDTO.getId());
                } else
                    logger.info("*****No rule conflict detected for parking system with id : {}", parkingSystemDTO.getId());
            }
        }
    }
}

