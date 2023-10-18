package com.example.system.services;

import com.example.system.dto.ParkingSystemDTO;
import com.example.system.dto.RuleDTO;
import com.example.system.dto.TransactionDTO;
import com.example.system.enums.TransactionPaymentType;
import com.example.system.factory.ParkingSystemServiceFactory;
import com.example.system.helperclasses.TransactionBuilder;
import com.example.system.models.*;
import com.example.system.exceptionhandlers.InvalidArgument;
import com.example.system.exceptionhandlers.NotFoundException;
import com.example.system.helperclasses.MutableDouble;
import com.example.system.helperclasses.ParkingResponse;
import com.example.system.repositories.ParkingSystemRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import com.example.system.interfaces.IParkingSystemService;

@Service
public class ParkingSystemService {
    private final TransactionBuilder builder;
    private final ParkingSystemRepo parkingSystemRepo;
    private final TransactionService transactionService;
    private final ModelMapper modelMapper;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    @Autowired
    public ParkingSystemService(TransactionBuilder builder, ParkingSystemRepo parkingSystemRepo, TransactionService transactionService, ModelMapper modelMapper) {
        this.builder = builder;
        this.parkingSystemRepo = parkingSystemRepo;
        this.transactionService = transactionService;
        this.modelMapper = modelMapper;
    }


    public List<ParkingSystemDTO> getAllParkingSystems() {
        Sort sort = Sort.by(Sort.Direction.ASC, "identifier");
        List<ParkingSystem> parkingSystems = parkingSystemRepo.findAll(sort);
        return parkingSystems.stream()
                .map(this::convertToDTO).toList();

    }

    public Optional<ParkingSystemDTO> getParkingSystemById(int id) {
        Optional<ParkingSystem> parkingSystem = parkingSystemRepo.findById(id);
        if (parkingSystem.isPresent()) {
            ParkingSystemDTO parkingSystemDTO = modelMapper.map(parkingSystem.get(), ParkingSystemDTO.class);
            return Optional.of(parkingSystemDTO);
        } else {
            return Optional.empty();
        }
    }

    public List<RuleDTO> getRulesForParkingSystem(int parkingSystemId) {
        return parkingSystemRepo.findById(parkingSystemId)
                .map(parkingSystem -> parkingSystem.getRules().stream()
                        .map(rule -> modelMapper.map(rule, RuleDTO.class))
                        .toList())
                .orElseThrow(() -> new NotFoundException("Parking system not found"));
    }

    public ParkingResponse getExitTime(LocalDateTime now, ParkingSystemDTO parkingSystemDTO, MutableDouble money, String plateNumber, TransactionPaymentType transactionPaymentType) {
        List<Rule> rules = parkingSystemDTO.getRules();
        IParkingSystemService service = ParkingSystemServiceFactory.createParkingSystemService();
        if (parkingSystemDTO.getRules() == null) {
            throw new NotFoundException("This parking system has no rule ");
        }
        LocalDateTime exitTime = service.calculateExitTime(now, money, rules);
        if (transactionPaymentType.equals(TransactionPaymentType.CASH)) {
            return new ParkingResponse(plateNumber, exitTime.format(formatter));
        } else throw new InvalidArgument("Sorry, this payment type is not available yet");
    }

    public TransactionDTO saveTransactionForParkingSystem(ParkingSystem parkingSystem, TransactionDTO transactionDTO) {
        TransactionDTO transaction = builder.buildTransactionDTO(parkingSystem
                , transactionDTO.getTransactionPaymentType()
                , transactionDTO.getEntryTime()
                , transactionDTO.getTransactionValue()
                , transactionDTO.getPlateNumber());
        double newTotalMoney = parkingSystem.getTotalMoney() + transactionDTO.getTransactionValue();
        parkingSystem.setTotalMoney(newTotalMoney);
        Transaction tr = modelMapper.map(transaction, Transaction.class);
        transactionService.saveTransaction(tr);
        parkingSystemRepo.save(parkingSystem);
        return transaction;
    }

    private ParkingSystemDTO convertToDTO(ParkingSystem parkingSystem) {
        ParkingSystemDTO parkingSystemDTO = new ParkingSystemDTO();
        parkingSystemDTO.setId(parkingSystem.getSystemId());
        parkingSystemDTO.setIdentifier(parkingSystem.getIdentifier());
        parkingSystemDTO.setAddress(parkingSystem.getAddress());
        parkingSystemDTO.setWorkingStatus(parkingSystem.getWorkingStatus());
        parkingSystemDTO.setFirstInstallDate(parkingSystem.getFirstInstallDate());
        parkingSystemDTO.setLastUpdate(parkingSystem.getLastUpdate());
        parkingSystemDTO.setTotalMoney(parkingSystem.getTotalMoney());
        parkingSystemDTO.setFirmwareVersion(parkingSystem.getFirmwareVersion());
        parkingSystemDTO.setRules(parkingSystem.getRules());
        return parkingSystemDTO;
    }
}