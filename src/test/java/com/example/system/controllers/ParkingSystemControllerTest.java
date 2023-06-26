package com.example.system.controllers;

import com.example.system.dto.ParkingSystemDTO;
import com.example.system.exceptionhandlers.InvalidArgument;
import com.example.system.exceptionhandlers.NotFoundException;
import com.example.system.helperclasses.MutableDouble;
import com.example.system.models.TransactionPaymentType;
import com.example.system.services.ParkingSystemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ParkingSystemControllerTest {
    @Mock
    private ParkingSystemService parkingSystemService;
    @Mock

    private ParkingSystemController parkingSystemController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        parkingSystemController = new ParkingSystemController(parkingSystemService);
    }

    @Test
    public void testGetParkingSystemById_ExistingId_ReturnsParkingSystemDTO() {
        int id = 1;
        ParkingSystemDTO parkingSystemDTO = new ParkingSystemDTO(/* add required data here */);
        Optional<ParkingSystemDTO> optionalParkingSystemDTO = Optional.of(parkingSystemDTO);

        when(parkingSystemService.getParkingSystemById(id)).thenReturn(optionalParkingSystemDTO);

        ParkingSystemDTO result = parkingSystemController.getParkingSystemById(id);

        assertEquals(parkingSystemDTO, result);
    }

    @Test
    public void testGetParkingSystemById_NonExistingId_ThrowsResponseStatusException() {
        int id = 1;
        Optional<ParkingSystemDTO> optionalParkingSystemDTO = Optional.empty();

        when(parkingSystemService.getParkingSystemById(id)).thenReturn(optionalParkingSystemDTO);

        assertThrows(ResponseStatusException.class, () -> parkingSystemController.getParkingSystemById(id));
    }

    @Test
    public void testGetAllParkingSystems_ReturnsListOfParkingSystemDTOs() {
        List<ParkingSystemDTO> parkingSystems = new ArrayList<>();
        parkingSystems.add(new ParkingSystemDTO(/* add required data here */));
        parkingSystems.add(new ParkingSystemDTO(/* add required data here */));
        // Add more ParkingSystemDTOs as needed

        when(parkingSystemService.getAllParkingSystems()).thenReturn(parkingSystems);

        List<ParkingSystemDTO> result = parkingSystemController.getAllParkingSystems();

        assertEquals(parkingSystems.size(), result.size());
        assertEquals(parkingSystems, result);
    }

    @Test
    public void testGetExitTime_InvalidId_ThrowsNotFoundException() {
        double money = 10.0;
        int id = 1;
        String plateNumber = "ABC123";
        TransactionPaymentType paymentType = TransactionPaymentType.CASH;
        LocalDateTime dateTime = LocalDateTime.now();
        MutableDouble moneyObject = new MutableDouble(money);
        Optional<ParkingSystemDTO> optionalParkingSystemDTO = Optional.empty();

        when(parkingSystemService.getParkingSystemById(id)).thenReturn(optionalParkingSystemDTO);

        assertThrows(NotFoundException.class, () ->
                parkingSystemController.getExitTime(money, id, plateNumber, paymentType, dateTime));
    }

    @Test
    public void testGetExitTime_NegativeMoney_ThrowsInvalidArgument() {
        double money = -10.0;
        int id = 1;
        String plateNumber = "ABC123";
        TransactionPaymentType paymentType = TransactionPaymentType.CASH;
        LocalDateTime dateTime = LocalDateTime.now();
        MutableDouble moneyObject = new MutableDouble(money);
        ParkingSystemDTO parkingSystemDTO = new ParkingSystemDTO(/* add required data here */);
        Optional<ParkingSystemDTO> optionalParkingSystemDTO = Optional.of(parkingSystemDTO);

        when(parkingSystemService.getParkingSystemById(id)).thenReturn(optionalParkingSystemDTO);

        assertThrows(InvalidArgument.class, () ->
                parkingSystemController.getExitTime(money, id, plateNumber, paymentType, dateTime));
    }
}