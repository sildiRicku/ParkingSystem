package com.example.system.controllers;

import com.example.system.dto.ParkingSystemDTO;
import com.example.system.dto.RuleDTO;
import com.example.system.dto.TransactionDTO;
import com.example.system.exceptionhandlers.InvalidArgument;
import com.example.system.exceptionhandlers.NotFoundException;
import com.example.system.helperclasses.MutableDouble;
import com.example.system.models.ParkingSystem;
import com.example.system.models.TransactionPaymentType;
import com.example.system.services.ParkingSystemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
class ParkingSystemControllerTest {
    @Mock
    private ParkingSystemService parkingSystemService;
    @Mock
    private ModelMapper modelMapper;
    @Mock

    private ParkingSystemController parkingSystemController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        parkingSystemController = new ParkingSystemController(parkingSystemService, modelMapper);

    }

    @Test
    void getParkingSystemById_ExistingId_ReturnsParkingSystemDTO() {
        int id = 1;
        ParkingSystemDTO parkingSystemDTO = new ParkingSystemDTO();
        Optional<ParkingSystemDTO> optionalParkingSystemDTO = Optional.of(parkingSystemDTO);

        when(parkingSystemService.getParkingSystemById(id)).thenReturn(optionalParkingSystemDTO);

        ParkingSystemDTO result = parkingSystemController.getParkingSystemById(id);

        assertEquals(parkingSystemDTO, result);
    }

    @Test
    void getParkingSystemById_NonExistingId_ThrowsResponseStatusException() {
        int id = 1;
        Optional<ParkingSystemDTO> optionalParkingSystemDTO = Optional.empty();

        when(parkingSystemService.getParkingSystemById(id)).thenReturn(optionalParkingSystemDTO);

        assertThrows(NotFoundException.class, () -> parkingSystemController.getParkingSystemById(id));
    }

    @Test
    void getAllParkingSystems_ReturnsListOfParkingSystemDTOs() {
        List<ParkingSystemDTO> parkingSystems = new ArrayList<>();
        parkingSystems.add(new ParkingSystemDTO());
        parkingSystems.add(new ParkingSystemDTO());

        when(parkingSystemService.getAllParkingSystems()).thenReturn(parkingSystems);

        List<ParkingSystemDTO> result = parkingSystemController.getAllParkingSystems();

        assertEquals(parkingSystems.size(), result.size());
        assertEquals(parkingSystems, result);
    }

    @Test
    void getExitTime_InvalidId_ThrowsNotFoundException() {
        double money = 10.0;
        int id = 1;
        String plateNumber = "ABC123";
        TransactionPaymentType paymentType = TransactionPaymentType.CASH;
        LocalDateTime dateTime = LocalDateTime.now();
        MutableDouble moneyObject = new MutableDouble(money);
        Optional<ParkingSystemDTO> optionalParkingSystemDTO = Optional.empty();

        when(parkingSystemService.getParkingSystemById(id)).thenReturn(optionalParkingSystemDTO);

        assertThrows(NotFoundException.class, () -> parkingSystemController.getExitTime(money, id, plateNumber, paymentType, dateTime));
    }

    @Test
    void getExitTime_NegativeMoney_ThrowsInvalidArgument() {
        double money = -10.0;
        int id = 1;
        String plateNumber = "ABC123";
        TransactionPaymentType paymentType = TransactionPaymentType.CASH;
        LocalDateTime dateTime = LocalDateTime.now();
        MutableDouble moneyObject = new MutableDouble(money);
        ParkingSystemDTO parkingSystemDTO = new ParkingSystemDTO();
        Optional<ParkingSystemDTO> optionalParkingSystemDTO = Optional.of(parkingSystemDTO);

        when(parkingSystemService.getParkingSystemById(id)).thenReturn(optionalParkingSystemDTO);

        assertThrows(InvalidArgument.class, () -> parkingSystemController.getExitTime(money, id, plateNumber, paymentType, dateTime));
    }

    @Test
    void getRulesForParkingSystem() {
        int parkingSystemId = 1;

        List<RuleDTO> mockRules = new ArrayList<>();
        mockRules.add(new RuleDTO());

        when(parkingSystemService.getRulesForParkingSystem(parkingSystemId)).thenReturn(mockRules);

        ResponseEntity<List<RuleDTO>> responseEntity = parkingSystemController.getRulesForParkingSystem(parkingSystemId);

        verify(parkingSystemService).getRulesForParkingSystem(parkingSystemId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockRules, responseEntity.getBody());
    }

    @Test
    void addTransaction_ReturnsTransactionDTO() {
        int parkingSystemId = 1;
        TransactionDTO transactionDTO = new TransactionDTO();

        ParkingSystemDTO parkingSystemDTO = new ParkingSystemDTO();
        ParkingSystem parkingSystem = new ParkingSystem(); // You might need to create a ParkingSystem object here.

        when(parkingSystemService.getParkingSystemById(parkingSystemId)).thenReturn(Optional.of(parkingSystemDTO));
        when(modelMapper.map(parkingSystemDTO, ParkingSystem.class)).thenReturn(parkingSystem);
        when(parkingSystemService.saveTransactionForParkingSystem(parkingSystem, transactionDTO)).thenReturn(transactionDTO);

        ResponseEntity<TransactionDTO> responseEntity = parkingSystemController.addTransaction(parkingSystemId, transactionDTO);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(transactionDTO, responseEntity.getBody());
    }

}