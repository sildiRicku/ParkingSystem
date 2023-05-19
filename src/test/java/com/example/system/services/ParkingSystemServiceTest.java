//package com.example.system.services;
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
package com.example.system.services;

import com.example.system.dto.ParkingSystemDTO;
import com.example.system.entities.Rule;
import com.example.system.entities.TransactionPaymentType;
import com.example.system.exceptionhandlers.NotFoundException;
import com.example.system.helperclasses.MutableDouble;
import com.example.system.helperclasses.ParkingResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class ParkingSystemServiceTest {

    @InjectMocks
    private ParkingSystemService parkingSystemService;

    @Mock
    private MutableDouble money;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCalExitTime() {
        LocalDateTime now = LocalDateTime.now();


        List<Rule> rules = new ArrayList<>();
        Rule rule1 = new Rule(10, LocalTime.of(8, 0), LocalTime.of(12, 0));
        Rule rule2 = new Rule(20, LocalTime.of(12, 0), LocalTime.of(16, 0));
        rules.add(rule1);
        rules.add(rule2);

        when(money.getValue()).thenReturn(50.0);

        LocalDateTime exitTime = parkingSystemService.calExitTime(now, money, rules);

        LocalDateTime expectedExitTime = now.plusHours(2);
        assertEquals(expectedExitTime, exitTime);
    }
    @Test
    void getExitTime_ReturnsParkingResponse() {
        LocalDateTime now = LocalDateTime.now();
        ParkingSystemDTO parkingSystemDTO = new ParkingSystemDTO();
        List<Rule> rules = new ArrayList<>();
        rules.add(new Rule(10.0, LocalTime.of(8, 0), LocalTime.of(12, 0)));
        rules.add(new Rule(5.0, LocalTime.of(14, 0), LocalTime.of(18, 0)));
        parkingSystemDTO.setRules(rules);
        MutableDouble money = new MutableDouble(50.0);
        String plateNumber = "ABC123";
        TransactionPaymentType transactionPaymentType = TransactionPaymentType.CASH;

        LocalDateTime exitTime = LocalDateTime.of(2023, 5, 19, 14, 30); // Set the expected exit time
        when(parkingSystemService.calExitTime(now, money, rules)).thenReturn(exitTime);

        ParkingResponse response = parkingSystemService.getExitTime(now, parkingSystemDTO, money, plateNumber, transactionPaymentType);

        assertEquals(new ParkingResponse(plateNumber, "2023-05-19 14:30"), response);
    }
        @Test
        void getExitTime() {
        LocalDateTime now = LocalDateTime.now();
        ParkingSystemDTO parkingSystemDTO = new ParkingSystemDTO();
        List<Rule> rules = null;
        parkingSystemDTO.setRules(rules);
        MutableDouble money = new MutableDouble(50.0);
        String plateNumber = "ABC123";
        TransactionPaymentType transactionPaymentType = TransactionPaymentType.CASH;

        assertThrows(NotFoundException.class, () ->
                parkingSystemService.getExitTime(now, parkingSystemDTO, money, plateNumber, transactionPaymentType)
        );
    }

}