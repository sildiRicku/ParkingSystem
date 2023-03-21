package com.example.system.controllers;

import com.example.system.dto.ParkingSystemDTO;
import com.example.system.entities.ParkingSystem;
import com.example.system.services.ParkingSystemService;
import com.example.system.services.RuleService;
import org.modelmapper.ModelMapper;
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
    private final RuleService ruleService;
    private final ModelMapper modelMapper;

    @Autowired
    public ParkingSystemController(ParkingSystemService parkingSystemService, RuleService ruleService, ModelMapper modelMapper) {
        this.parkingSystemService = parkingSystemService;
        this.ruleService = ruleService;
        this.modelMapper = modelMapper;
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
    public ParkingSystemDTO addParkingSystem(@RequestBody ParkingSystemDTO parkingSystemDTO) {
        return parkingSystemService.addParkingSystem(parkingSystemDTO);
    }

    @GetMapping("/hours")
    public String getHoursForMoney(@RequestParam("money") int money, @RequestParam("id") int parkingId) {
        Optional<ParkingSystemDTO> parkingSystemDTO = parkingSystemService.getParkingSystemById(parkingId);
        if (parkingSystemDTO.isEmpty()) {
            return "Parking system with id " + parkingId + " is not found";
        }
        ParkingSystem parkingSystem = modelMapper.map(parkingSystemDTO, ParkingSystem.class);
        return ruleService.getHoursForMoney(money, parkingSystem);
    }
}