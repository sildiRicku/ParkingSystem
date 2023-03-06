package com.example.system.services;

import com.example.system.entities.ParkingSystem;
import com.example.system.repositories.ParkingSystemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParkingSystemService {
    private final ParkingSystemRepo parkingSystemRepo;

    @Autowired
    public ParkingSystemService(ParkingSystemRepo parkingSystemRepo) {
        this.parkingSystemRepo = parkingSystemRepo;
    }

    public List<ParkingSystem> getAllParkingSystems() {
        return parkingSystemRepo.findAll();
    }

    public Optional<ParkingSystem> getParkingSystemById(int id) {
        return parkingSystemRepo.findById(id);
    }


}
