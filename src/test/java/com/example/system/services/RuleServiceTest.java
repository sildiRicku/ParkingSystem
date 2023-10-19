package com.example.system.services;

import com.example.system.models.Rule;
import com.example.system.serviceimplementations.TimeUtils;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RuleServiceTest {
    private TimeUtils timeUtils = mock(TimeUtils.class);
    private RuleService ruleConflictChecker = new RuleService(timeUtils);

    @Test
    void checkRuleConflict() {
        List<Rule> rules = new ArrayList<>();
        Rule r1 = Mockito.mock(Rule.class);
        Rule r2 = Mockito.mock(Rule.class);

        rules.add(r1);
        rules.add(r2);

        when(timeUtils.timeRangesOverlap(LocalTime.of(9, 0), LocalTime.of(12, 0),
                LocalTime.of(11, 0), LocalTime.of(14, 0))).thenReturn(true);

        assertFalse(ruleConflictChecker.checkRuleConflict(rules));
    }

    @Test
    void checkRuleConflictNoConflict() {
        List<Rule> rules = new ArrayList<>();
        Rule r1 = Mockito.mock(Rule.class);
        Rule r2 = Mockito.mock(Rule.class);

        rules.add(r1);
        rules.add(r2);

        when(timeUtils.timeRangesOverlap(LocalTime.of(9, 0), LocalTime.of(10, 0),
                LocalTime.of(11, 0), LocalTime.of(12, 0))).thenReturn(false);

        assertFalse(ruleConflictChecker.checkRuleConflict(rules));
    }
}