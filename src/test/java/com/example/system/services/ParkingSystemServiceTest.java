package com.example.system.services;//package com.example.system.services;
//
//import com.example.system.dto.ParkingSystemDTO;
//import com.example.system.entities.TransactionPaymentType;
//import com.example.system.exceptionhandlers.InvalidArgument;
//import com.example.system.exceptionhandlers.NotFoundException;
//import com.example.system.helperclasses.MutableDouble;
//import com.example.system.helperclasses.ParkingResponse;
//import com.example.system.entities.Rule;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.mockito.junit.MockitoJUnitRunner;
//
//import java.time.LocalDateTime;
//import java.time.LocalTime;
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.Mockito.when;
//@RunWith(MockitoJUnitRunner.class)
//class ParkingSystemServiceTest {
//
//    @InjectMocks
//    private ParkingSystemService parkingSystemService;
//    @Mock
//    private Rule rules;
//    @Mock
//    private MutableDouble money;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void getExitTime_ReturnsParkingResponse() {
//        LocalDateTime now = LocalDateTime.now();
//        ParkingSystemDTO parkingSystemDTO = new ParkingSystemDTO();
//        List<Rule> rules = new ArrayList<>();
//        rules.add(new Rule(10.0, LocalTime.of(8, 0), LocalTime.of(12, 0)));
//        rules.add(new Rule(5.0, LocalTime.of(14, 0), LocalTime.of(18, 0)));
//        parkingSystemDTO.setRules(rules);
//        MutableDouble money = new MutableDouble(50.0);
//        String plateNumber = "ABC123";
//        TransactionPaymentType transactionPaymentType = TransactionPaymentType.CASH;
//
//        LocalDateTime exitTime = LocalDateTime.of(2023, 5, 19, 14, 30); // Set the expected exit time
//        when(parkingSystemService.calExitTime(now, money, rules)).thenReturn(exitTime);
//
//        ParkingResponse response = parkingSystemService.getExitTime(now, parkingSystemDTO, money, plateNumber, transactionPaymentType);
//
//        assertEquals(new ParkingResponse(plateNumber, "2023-05-19 14:30"), response);
//    }
//
//    @Test
//    void getExitTime() {
//        LocalDateTime now = LocalDateTime.now();
//        ParkingSystemDTO parkingSystemDTO = new ParkingSystemDTO();
//        List<Rule> rules = null;
//        parkingSystemDTO.setRules(rules);
//        MutableDouble money = new MutableDouble(50.0);
//        String plateNumber = "ABC123";
//        TransactionPaymentType transactionPaymentType = TransactionPaymentType.CASH;
//
//        assertThrows(NotFoundException.class, () ->
//                parkingSystemService.getExitTime(now, parkingSystemDTO, money, plateNumber, transactionPaymentType)
//        );
//    }
//
//    @Test
//    void getExitTime_WithInvalidPaymentType_ThrowsInvalidArgument() {
//        LocalDateTime now = LocalDateTime.now();
//        ParkingSystemDTO parkingSystemDTO = new ParkingSystemDTO();
//        List<Rule> rules = new ArrayList<>();
//        parkingSystemDTO.setRules(rules);
//        MutableDouble money = new MutableDouble(50.0);
//        String plateNumber = "ABC123";
//        TransactionPaymentType transactionPaymentType = TransactionPaymentType.CARD;
//
//        assertThrows(InvalidArgument.class, () ->
//                parkingSystemService.getExitTime(now, parkingSystemDTO, money, plateNumber, transactionPaymentType)
//        );
//    }
//}

//here
//package com.example.system.services;
//
//import com.example.system.dto.ParkingSystemDTO;
//import com.example.system.entities.Rule;
//import com.example.system.entities.TransactionPaymentType;
//import com.example.system.exceptionhandlers.NotFoundException;
//import com.example.system.helperclasses.MutableDouble;
//import com.example.system.helperclasses.ParkingResponse;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.MockitoAnnotations;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.time.LocalDateTime;
//import java.time.LocalTime;
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.Mockito.when;
//@ExtendWith(MockitoExtension.class)
//class ParkingSystemServiceTest {
//
//    @InjectMocks
//    private ParkingSystemService parkingSystemService;
//
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void getExitTime_ReturnsParkingResponse() {
//        LocalDateTime now = LocalDateTime.now();
//        ParkingSystemDTO parkingSystemDTO = new ParkingSystemDTO();
//        List<Rule> rules = new ArrayList<>();
//        rules.add(new Rule(10.0, LocalTime.of(8, 0), LocalTime.of(12, 0)));
//        rules.add(new Rule(5.0, LocalTime.of(14, 0), LocalTime.of(18, 0)));
//        parkingSystemDTO.setRules(rules);
//        MutableDouble money = new MutableDouble(50.0);
//        String plateNumber = "ABC123";
//        TransactionPaymentType transactionPaymentType = TransactionPaymentType.CASH;
//
//        LocalDateTime exitTime = LocalDateTime.of(2023, 5, 19, 14, 30); // Set the expected exit time
//        when(parkingSystemService.calExitTime(now, money, rules)).thenReturn(exitTime);
//
//        ParkingResponse response = parkingSystemService.getExitTime(now, parkingSystemDTO, money, plateNumber, transactionPaymentType);
//
//        assertEquals(new ParkingResponse(plateNumber, "2023-05-19 14:30"), response);
//    }
//        @Test
//        void getExitTime() {
//        LocalDateTime now = LocalDateTime.now();
//        ParkingSystemDTO parkingSystemDTO = new ParkingSystemDTO();
//        List<Rule> rules = null;
//        parkingSystemDTO.setRules(rules);
//        MutableDouble money = new MutableDouble(50.0);
//        String plateNumber = "ABC123";
//        TransactionPaymentType transactionPaymentType = TransactionPaymentType.CASH;
//
//        assertThrows(NotFoundException.class, () ->
//                parkingSystemService.getExitTime(now, parkingSystemDTO, money, plateNumber, transactionPaymentType)
//        );
//    }
//
//}

import com.example.system.entities.Rule;
import com.example.system.helperclasses.MutableDouble;
import com.example.system.repositories.ParkingSystemRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ParkingSystemServiceTest {
    @InjectMocks
    private ParkingSystemService parkingSystemService;
    @Mock
    private ParkingSystemRepo parkingSystemRepo;
    @Mock
    private ModelMapper modelMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCalulateRemainTimePerRule() {
        // Create mock objects
        Rule rule = mock(Rule.class);
        MutableDouble remainMoney = mock(MutableDouble.class);

        // Set up the mock objects
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime endTime = now.plusHours(1);
        when(rule.getCost()).thenReturn(10.0);
        when(rule.getEndTime()).thenReturn(endTime.toLocalTime());
        when(remainMoney.getValue()).thenReturn(20.0);

        // Call the method
        LocalDateTime result = parkingSystemService.calulateRemainTimePerRule(now, rule, remainMoney);

        // Verify the behavior and assertions
        assertEquals(now.plusHours(1).withSecond(0).withNano(0), result);

        // Verify that the remainMoney.setValue() method was called with the correct argument
        ArgumentCaptor<Double> argumentCaptor = ArgumentCaptor.forClass(Double.class);
        verify(remainMoney).setValue(argumentCaptor.capture());
        assertEquals(10.0, argumentCaptor.getValue());
    }

    @Test
    void testCalExitTime() {
        // Create mock objects
        MutableDouble money = mock(MutableDouble.class);
        Rule rule = mock(Rule.class);
        List<Rule> rules = new ArrayList<>();
        rules.add(rule);

        // Set up the mock objects
        LocalDateTime now = LocalDateTime.now();
        when(money.getValue()).thenReturn(10.0);
        when(rule.getCost()).thenReturn(5.0);
        when(rule.getStartTime()).thenReturn(now.toLocalTime().minusHours(1));
        when(rule.getEndTime()).thenReturn(now.toLocalTime().plusHours(1));

        // Create an instance of the class under test
        ParkingSystemService yourClass = new ParkingSystemService(parkingSystemRepo, modelMapper);

        // Call the method
        LocalDateTime result = yourClass.calExitTime(now, money, rules);

        // Verify the behavior and assertions
        assertEquals(now.plusHours(1), result);

        // Verify that the calulateRemainTimePerRule method was called with the correct arguments
        verify(yourClass, atLeastOnce()).calulateRemainTimePerRule(any(), any(), any());
    }

}

