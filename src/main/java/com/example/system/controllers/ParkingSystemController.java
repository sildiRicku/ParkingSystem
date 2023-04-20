package com.example.system.controllers;

import com.example.system.classes.ParkingResponse;
import com.example.system.dto.ParkingSystemDTO;
import com.example.system.entities.TransactionPaymentType;
import com.example.system.exceptionhandlers.InvalidArgument;
import com.example.system.exceptionhandlers.NotFoundException;
import com.example.system.services.ParkingSystemService;
import com.example.system.services.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/parking-system")
public class ParkingSystemController {

    private final ParkingSystemService parkingSystemService;
    private final RuleService ruleService;

    @Autowired
    public ParkingSystemController(ParkingSystemService parkingSystemService, RuleService ruleService) {
        this.parkingSystemService = parkingSystemService;
        this.ruleService = ruleService;
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

    @GetMapping("/parking-time")
    public ParkingResponse getHoursForMoney(@RequestParam(value = "dateTime", required = false, defaultValue = "${date.now}") LocalDateTime dateTime,
                                            @RequestParam("money") double money,
                                            @RequestParam("plateNumber") String plateNumber,
                                            @RequestParam("id") int parkingId,
                                            @RequestParam("transactionPaymentType") TransactionPaymentType transactionPaymentType) throws IllegalArgumentException {
        if (money < 0) {
            throw new InvalidArgument("You cannot use a negative money value");
        }
        Optional<ParkingSystemDTO> parkingSystemDTO = parkingSystemService.getParkingSystemById(parkingId);
        if (parkingSystemDTO.isEmpty()) {
            throw new NotFoundException("Parking system with id: " + parkingId + "  not found");
        }
        return ruleService.getExitTime(dateTime, money, plateNumber, parkingSystemDTO.get(), transactionPaymentType);

    }

}


