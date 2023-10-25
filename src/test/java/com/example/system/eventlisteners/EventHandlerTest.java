package com.example.system.eventlisteners;

import static org.junit.jupiter.api.Assertions.*;

import com.example.system.dto.ParkingSystemDTO;
import com.example.system.models.Rule;
import com.example.system.services.ParkingSystemService;
import com.example.system.services.RuleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyList;

class EventHandlerTest {

    @Mock
    private ParkingSystemService parkingSystemService;

    @Mock
    private RuleService ruleService;

    @InjectMocks
    private EventHandler eventHandler;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void handleContextRefreshEvent() {
        List<ParkingSystemDTO> parkingSystemDTOS = new ArrayList<>();
        ParkingSystemDTO parkingSystemDTO = Mockito.mock(ParkingSystemDTO.class);
        parkingSystemDTO.setId(1);
        List<Rule> rules = new ArrayList<>();
        parkingSystemDTO.setRules(rules);
        parkingSystemDTOS.add(parkingSystemDTO);

        Mockito.when(parkingSystemService.getAllParkingSystems()).thenReturn(parkingSystemDTOS);
        Mockito.when(ruleService.checkRuleConflict(anyList())).thenReturn(false);

        eventHandler.handleContextRefreshEvent();

    }
}
