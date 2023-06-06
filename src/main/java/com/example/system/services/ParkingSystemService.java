package com.example.system.services;

import com.example.system.dto.ParkingSystemDTO;
import com.example.system.factory.ParkingSystemServiceFactory;
import com.example.system.models.ParkingSystem;
import com.example.system.models.Rule;
import com.example.system.models.TransactionPaymentType;
import com.example.system.exceptionhandlers.InvalidArgument;
import com.example.system.exceptionhandlers.NotFoundException;
import com.example.system.helperclasses.MutableDouble;
import com.example.system.helperclasses.ParkingResponse;
import com.example.system.repositories.ParkingSystemRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.example.system.interfaces.ParkingSystemServicee;

@Service
public class ParkingSystemService {
    private final ParkingSystemRepo parkingSystemRepo;
    private final ModelMapper modelMapper;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");


    @Autowired
    public ParkingSystemService(ParkingSystemRepo parkingSystemRepo, ModelMapper modelMapper) {
        this.parkingSystemRepo = parkingSystemRepo;
        this.modelMapper = modelMapper;
    }


    public List<ParkingSystemDTO> getAllParkingSystems() {
        List<ParkingSystem> parkingSystems = parkingSystemRepo.findAll();
        List<ParkingSystemDTO> parkingSystemDTOS = new ArrayList<>();
        for (ParkingSystem parkingSystem : parkingSystems) {
            ParkingSystemDTO parkingSystemDTO = modelMapper.map(parkingSystem, ParkingSystemDTO.class);
            parkingSystemDTOS.add(parkingSystemDTO);
        }
        return parkingSystemDTOS;
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


    public ParkingSystemDTO addParkingSystem(ParkingSystemDTO parkingSystemDTO) {
        ParkingSystem parkingSystem = modelMapper.map(parkingSystemDTO, ParkingSystem.class);
        parkingSystemRepo.save(parkingSystem);
        parkingSystemDTO = modelMapper.map(parkingSystem, ParkingSystemDTO.class);
        return parkingSystemDTO;
    }


    public ParkingResponse getExitTime(LocalDateTime now, ParkingSystemDTO parkingSystemDTO, MutableDouble money, String plateNumber, TransactionPaymentType transactionPaymentType) {
        List<Rule> rules = parkingSystemDTO.getRules();
        ParkingSystemServicee service=ParkingSystemServiceFactory.createParkingSystemService();
        if (parkingSystemDTO.getRules() == null) {
            throw new NotFoundException("This parking system has no rule ");
        }
        LocalDateTime exitTime = service.calculateExitTime(now, money, rules);
        if (transactionPaymentType.equals(TransactionPaymentType.CASH)) {
            return new ParkingResponse(plateNumber, exitTime.format(formatter));
        } else throw new InvalidArgument("Sorry, this payment type is not available yet");
    }
}
