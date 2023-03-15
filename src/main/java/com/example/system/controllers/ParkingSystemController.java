package com.example.system.controllers;

import com.example.system.dto.ParkingSystemDTO;
import com.example.system.entities.ParkingSystem;
import com.example.system.services.ParkingSystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/parking-system")
public class ParkingSystemController {

    private final ParkingSystemService parkingSystemService;

    @Autowired
    public ParkingSystemController(ParkingSystemService parkingSystemService) {
        this.parkingSystemService = parkingSystemService;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ParkingSystemDTO getParkingSystemById(@PathVariable int id) {
        Optional<ParkingSystemDTO> parkingSystemDTO = parkingSystemService.getParkingSystemById(id);
        if (parkingSystemDTO.isPresent()) {
            return parkingSystemDTO.get();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);


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