package com.example.system.services;

import com.example.system.dto.ParkingSystemDTO;
import com.example.system.exceptionhandlers.InvalidArgument;
import com.example.system.exceptionhandlers.NotFoundException;
import com.example.system.factory.ParkingSystemServiceFactory;
import com.example.system.helperclasses.MutableDouble;
import com.example.system.helperclasses.ParkingResponse;
import com.example.system.interfaces.IParkingSystemService;
import com.example.system.models.ParkingSystem;
import com.example.system.models.Rule;
import com.example.system.models.TransactionPaymentType;
import com.example.system.models.Transactions;
import com.example.system.repositories.ParkingSystemRepo;
import com.example.system.serviceimplementations.ParkingSystemServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.any;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class ParkingSystemServiceTest {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    @Mock
    private ParkingSystemRepo parkingSystemRepo;

    @Mock
    private ModelMapper modelMapper;

    private ParkingSystemService parkingSystemService;
    @Mock
    private IParkingSystemService parkingSystemServiceMock;
    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        parkingSystemService = new ParkingSystemService(parkingSystemRepo, modelMapper);
    }


    @Test
    public void testGetParkingSystemById_ExistingId_ReturnsOptionalParkingSystemDTO() {
        int id = 1;
        ParkingSystem parkingSystem = new ParkingSystem(/* add required data here */);
        Optional<ParkingSystem> optionalParkingSystem = Optional.of(parkingSystem);

        when(parkingSystemRepo.findById(id)).thenReturn(optionalParkingSystem);

        ParkingSystemDTO expectedDTO = new ParkingSystemDTO(/* add expected data here */);
        when(modelMapper.map(parkingSystem, ParkingSystemDTO.class)).thenReturn(expectedDTO);

        Optional<ParkingSystemDTO> result = parkingSystemService.getParkingSystemById(id);

        assertTrue(result.isPresent());
        assertEquals(expectedDTO, result.get());
    }

    @Test
    public void testGetParkingSystemById_NonExistingId_ReturnsEmptyOptional() {
        int id = 1;
        Optional<ParkingSystem> optionalParkingSystem = Optional.empty();

        when(parkingSystemRepo.findById(id)).thenReturn(optionalParkingSystem);

        Optional<ParkingSystemDTO> result = parkingSystemService.getParkingSystemById(id);

        assertTrue(result.isEmpty());
    }



    @Test
    public void testGetExitTime_NoRules_ThrowsNotFoundException() {
        LocalDateTime now = LocalDateTime.now();
        ParkingSystemDTO parkingSystemDTO = new ParkingSystemDTO(/* add required data here */);
        double money = 10.0;
        MutableDouble moneyObject = new MutableDouble(money);
        String plateNumber = "ABC123";
        TransactionPaymentType paymentType = TransactionPaymentType.CASH;
        List<Rule> rules = null;

        assertThrows(NotFoundException.class, () ->
                parkingSystemService.getExitTime(now, parkingSystemDTO, moneyObject, plateNumber, paymentType));
    }

    @Test
    public void testGetExitTime_InvalidPaymentType_ThrowsInvalidArgument() {
        ParkingSystemDTO parkingSystemDTO = new ParkingSystemDTO();
        LocalDateTime now = LocalDateTime.now();
        double money = 10.0;
        MutableDouble moneyObject = new MutableDouble(money);
        String plateNumber = "ABC123";
        TransactionPaymentType paymentType = TransactionPaymentType.CARD;
        List<Rule> rules = new ArrayList<>();
        // Add rules as needed

        assertThrows(NotFoundException.class, () ->
                parkingSystemService.getExitTime(now, parkingSystemDTO, moneyObject, plateNumber, paymentType));
    }
    @Test
    public void testGetAllParkingSystems() {
        // Create test data
        List<ParkingSystem> parkingSystems = new ArrayList<>();
        parkingSystems.add(new ParkingSystem(/* add required data here */));
        parkingSystems.add(new ParkingSystem(/* add required data here */));

        List<ParkingSystemDTO> expectedDTOs = new ArrayList<>();
        expectedDTOs.add(new ParkingSystemDTO(/* add expected mapping here */));
        expectedDTOs.add(new ParkingSystemDTO(/* add expected mapping here */));

        // Mock the repository behavior
        Sort sort = Sort.by(Sort.Direction.ASC, "identifier");
        when(parkingSystemRepo.findAll(sort)).thenReturn(parkingSystems);
        when(modelMapper.map(parkingSystems.get(0), ParkingSystemDTO.class)).thenReturn(expectedDTOs.get(0));
        when(modelMapper.map(parkingSystems.get(1), ParkingSystemDTO.class)).thenReturn(expectedDTOs.get(1));

        // Call the method
        List<ParkingSystemDTO> result = parkingSystemService.getAllParkingSystems();

        // Verify the result
        assertEquals(expectedDTOs, result);
    }


    @Test
    public void testGetExitTime_NonCashPaymentType_ThrowsInvalidArgument() {
        LocalDateTime now = LocalDateTime.now();
        ParkingSystemDTO parkingSystemDTO = new ParkingSystemDTO(/* add required data here */);
        double money = 10.0;
        MutableDouble moneyObject = new MutableDouble(money);
        String plateNumber = "ABC123";
        TransactionPaymentType paymentType = TransactionPaymentType.CARD;
        List<Rule> rules = new ArrayList<>();
        // Add rules as needed

        assertThrows(NotFoundException.class, () -> parkingSystemService.getExitTime(now, parkingSystemDTO, moneyObject, plateNumber, paymentType));
    }
}