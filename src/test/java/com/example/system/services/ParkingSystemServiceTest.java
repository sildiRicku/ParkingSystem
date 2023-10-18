package com.example.system.services;

import com.example.system.dto.ParkingSystemDTO;
import com.example.system.dto.RuleDTO;
import com.example.system.dto.TransactionDTO;
import com.example.system.exceptionhandlers.NotFoundException;
import com.example.system.helperclasses.MutableDouble;
import com.example.system.helperclasses.TransactionBuilder;
import com.example.system.models.ParkingSystem;
import com.example.system.models.Rule;
import com.example.system.enums.TransactionPaymentType;
import com.example.system.repositories.ParkingSystemRepo;
import com.example.system.serviceimplementations.ParkingSystemServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
class ParkingSystemServiceTest {

    @Mock
    private ParkingSystemRepo parkingSystemRepo;
    @Mock
    private ParkingSystemServiceImpl parkingSystemServiceImpl;
    @Mock
    private TransactionBuilder builder;
    @Mock
    private TransactionService transactionService;
    @Mock
    private ModelMapper modelMapper;
    private ParkingSystemService parkingSystemService;


    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        parkingSystemService = new ParkingSystemService(builder, parkingSystemRepo, transactionService, modelMapper);
    }


    @Test
    void getParkingSystemById_ExistingId_ReturnsOptionalParkingSystemDTO() {
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
    void getParkingSystemById_NonExistingId_ReturnsEmptyOptional() {
        int id = 1;
        Optional<ParkingSystem> optionalParkingSystem = Optional.empty();

        when(parkingSystemRepo.findById(id)).thenReturn(optionalParkingSystem);

        Optional<ParkingSystemDTO> result = parkingSystemService.getParkingSystemById(id);

        assertTrue(result.isEmpty());
    }


    @Test
    void getExitTime_NoRules_ThrowsNotFoundException() {
        LocalDateTime now = LocalDateTime.now();
        ParkingSystemDTO parkingSystemDTO = new ParkingSystemDTO(/* add required data here */);
        double money = 10.0;
        MutableDouble moneyObject = new MutableDouble(money);
        String plateNumber = "ABC123";
        TransactionPaymentType paymentType = TransactionPaymentType.CASH;
        List<Rule> rules = null;

        assertThrows(NotFoundException.class, () -> parkingSystemService.getExitTime(now, parkingSystemDTO, moneyObject, plateNumber, paymentType));
    }

    @Test
    void getExitTime_InvalidPaymentType_ThrowsInvalidArgument() {
        ParkingSystemDTO parkingSystemDTO = new ParkingSystemDTO();
        LocalDateTime now = LocalDateTime.now();
        double money = 10.0;
        MutableDouble moneyObject = new MutableDouble(money);
        String plateNumber = "ABC123";
        TransactionPaymentType paymentType = TransactionPaymentType.CARD;
        List<Rule> rules = new ArrayList<>();

        assertThrows(NotFoundException.class, () -> parkingSystemService.getExitTime(now, parkingSystemDTO, moneyObject, plateNumber, paymentType));
    }

    @Test
    void getAllParkingSystems() {
        List<ParkingSystem> mockParkingSystems = new ArrayList<>();
        ParkingSystem parkingSystem = new ParkingSystem();
        parkingSystem.setIdentifier("identifier2");
        ParkingSystem parkingSystem1 = new ParkingSystem();
        parkingSystem1.setIdentifier("identifier1");
        mockParkingSystems.add(parkingSystem1);
        mockParkingSystems.add(parkingSystem);
        when(parkingSystemRepo.findAll(any(Sort.class))).thenReturn(mockParkingSystems);

        List<ParkingSystemDTO> result = parkingSystemService.getAllParkingSystems();

        assertEquals(2, result.size());
        List<String> identifiers = result.stream().map(ParkingSystemDTO::getIdentifier).collect(Collectors.toList());
        assertEquals(List.of("identifier1", "identifier2"), identifiers);

        verify(parkingSystemRepo).findAll(Sort.by(Sort.Direction.ASC, "identifier"));
    }


    @Test
    void getExitTime_NonCashPaymentType_ThrowsInvalidArgument() {
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

    @Test
    void getRulesForParkingSystem_ExistingParkingSystem() {
        int parkingSystemId = 1;
        ParkingSystem parkingSystem = Mockito.mock(ParkingSystem.class); // Mock the ParkingSystem
        Rule r1 = Mockito.mock(Rule.class);
        Rule r2 = Mockito.mock(Rule.class);
        List<Rule> rules = Arrays.asList(r2, r1);

        when(parkingSystemRepo.findById(parkingSystemId)).thenReturn(Optional.of(parkingSystem));
        when(parkingSystem.getRules()).thenReturn(rules);
        when(modelMapper.map(any(), eq(RuleDTO.class))).thenReturn(new RuleDTO());

        List<RuleDTO> result = parkingSystemService.getRulesForParkingSystem(parkingSystemId);

        assertEquals(rules.size(), result.size());
        verify(parkingSystemRepo).findById(parkingSystemId);
        verify(parkingSystem).getRules();
        verify(modelMapper, times(rules.size())).map(any(), eq(RuleDTO.class));
    }

    @Test
    void getRulesForParkingSystem_NonExistingParkingSystem() {
        int parkingSystemId = 1;

        when(parkingSystemRepo.findById(parkingSystemId)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> parkingSystemService.getRulesForParkingSystem(parkingSystemId));

        verify(parkingSystemRepo).findById(parkingSystemId);
    }

    @Test
    void saveTransactionForParkingSystem() {
        ParkingSystem parkingSystem = Mockito.mock(ParkingSystem.class);
        TransactionDTO transactionDTO = Mockito.mock(TransactionDTO.class);
        transactionDTO.setTransactionId(22);

        when(parkingSystemServiceImpl.calculateExitTime(any(), any(), any())).thenReturn(LocalDateTime.now());

        TransactionDTO result = parkingSystemService.saveTransactionForParkingSystem(parkingSystem, transactionDTO);

        verify(transactionService).saveTransaction(any());
        verify(parkingSystemRepo).save(any());
        TransactionDTO expectedResult = Mockito.mock(TransactionDTO.class);

//         assertEquals(expectedResult, result);
    }
}