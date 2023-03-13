package com.example.system.services;

import com.example.system.dto.ParkingSystemDTO;
import com.example.system.entities.ParkingSystem;
import com.example.system.repositories.ParkingSystemRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class ParkingSystemService {
    private final ParkingSystemRepo parkingSystemRepo;
    private ModelMapper modelMapper;

    @Autowired
    public ParkingSystemService(ParkingSystemRepo parkingSystemRepo, ModelMapper modelMapper) {
        this.parkingSystemRepo = parkingSystemRepo;
        this.modelMapper = modelMapper;
    }


    public List<ParkingSystem> getAllParkingSystems() {
        return parkingSystemRepo.findAll();
    }

    public Optional<ParkingSystem> getParkingSystemById(int id) {
        return parkingSystemRepo.findById(id);
    }

    public ParkingSystem addParkingSystem(ParkingSystemDTO parkingSystemDTO) {
        ParkingSystem parkingSystem = modelMapper.map(parkingSystemDTO, ParkingSystem.class);
        return parkingSystemRepo.save(parkingSystem);
    }
}
