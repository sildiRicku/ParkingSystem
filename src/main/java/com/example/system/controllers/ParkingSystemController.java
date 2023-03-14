package com.example.system.controllers;

import com.example.system.dto.ParkingSystemDTO;
import com.example.system.entities.ParkingSystem;
import com.example.system.services.ParkingSystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parking-system")
public class ParkingSystemController {

    private final ParkingSystemService parkingSystemService;

    @Autowired
    public ParkingSystemController(ParkingSystemService parkingSystemService) {
        this.parkingSystemService = parkingSystemService;
    }

    @GetMapping("/{id}")
    public ParkingSystemDTO getParkingSystemById(@PathVariable int id) {
        return parkingSystemService.getParkingSystemById(id).orElse(null);

    }

    @GetMapping("/all")
    public List<ParkingSystemDTO> getAllParkingSystems() {
        return parkingSystemService.getAllParkingSystems();
    }

    @PostMapping("/new")
    public ParkingSystem addParkingSystem(@RequestBody ParkingSystemDTO parkingSystemDTO) {
        return parkingSystemService.addParkingSystem(parkingSystemDTO);
    }
}