package com.example.system.controllers;

import com.example.system.dto.ParkingSystemDTO;
import com.example.system.services.ParkingSystemService;
import com.example.system.services.RuleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class RuleControllerTest {

    @InjectMocks
    private RuleController ruleController;

    @Mock
    private RuleService ruleService;

    @Mock
    private ParkingSystemService parkingSystemService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCheckRuleConflictWithConflict() {
        when(ruleService.checkRuleConflict(Collections.emptyList())).thenReturn(true);

        ParkingSystemDTO parkingSystem = new ParkingSystemDTO();
        parkingSystem.setId(1);
        when(parkingSystemService.getParkingSystemById(1)).thenReturn(Optional.of(parkingSystem));

        ResponseEntity<String> responseEntity = ruleController.checkRuleConflict(1);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("No rule conflicts.", responseEntity.getBody());
    }

    @Test
    void testCheckRuleConflictWithoutConflict() {
        when(ruleService.checkRuleConflict(Collections.emptyList())).thenReturn(false);

        ParkingSystemDTO parkingSystem = new ParkingSystemDTO();
        parkingSystem.setId(2);
        when(parkingSystemService.getParkingSystemById(2)).thenReturn(Optional.of(parkingSystem));

        ResponseEntity<String> responseEntity = ruleController.checkRuleConflict(2);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("No rule conflicts.", responseEntity.getBody());
    }

    @Test
    void testCheckRuleConflictWithInvalidParkingSystemId() {
        when(parkingSystemService.getParkingSystemById(3)).thenReturn(Optional.empty());
    }
}

   
