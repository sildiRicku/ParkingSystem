package com.example.system.services;

import com.example.system.dto.ParkingSystemDTO;
import com.example.system.entities.ParkingSystem;
import com.example.system.repositories.ParkingSystemRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ParkingSystemService {
    private final ParkingSystemRepo parkingSystemRepo;
    private final ModelMapper modelMapper;

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
            ParkingSystemDTO parkingSystemDTO = modelMapper.map(parkingSystem, ParkingSystemDTO.class);
            return Optional.of(parkingSystemDTO);
        } else {
            return Optional.empty();
        }
    }

    public ParkingSystem addParkingSystem(ParkingSystemDTO parkingSystemDTO) {
        ParkingSystem parkingSystem = modelMapper.map(parkingSystemDTO, ParkingSystem.class);
        return parkingSystemRepo.save(parkingSystem);
    }
}
