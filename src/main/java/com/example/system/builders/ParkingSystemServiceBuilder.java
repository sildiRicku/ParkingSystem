package com.example.system.builders;

import com.example.system.repositories.ParkingSystemRepo;
import com.example.system.services.ParkingSystemService;
import org.modelmapper.ModelMapper;

public class ParkingSystemServiceBuilder {
    private ParkingSystemRepo parkingSystemRepo;
    private ModelMapper modelMapper;

    public ParkingSystemServiceBuilder setParkingSystemRepo(ParkingSystemRepo parkingSystemRepo) {
        this.parkingSystemRepo = parkingSystemRepo;
        return this;
    }

    public ParkingSystemServiceBuilder setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        return this;
    }

    public ParkingSystemService createParkingSystemService() {
        return new ParkingSystemService(parkingSystemRepo, modelMapper);
    }
}