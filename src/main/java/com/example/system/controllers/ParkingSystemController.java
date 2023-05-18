package com.example.system.controllers;

import com.example.system.entities.TransactionPaymentType;
import com.example.system.exceptionhandlers.InvalidArgument;
import com.example.system.exceptionhandlers.NotFoundException;
import com.example.system.helperclasses.MutableDouble;
import com.example.system.dto.ParkingSystemDTO;
import com.example.system.helperclasses.ParkingResponse;
import com.example.system.services.ParkingSystemService;
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
    public ParkingSystemDTO addParkingSystem(@RequestBody ParkingSystemDTO parkingSystemDTO) {
        return parkingSystemService.addParkingSystem(parkingSystemDTO);
    }

    @GetMapping("/parking-time")
    public ParkingResponse getExitTime(@RequestParam("money") double money,
                                       @RequestParam("id") int id,
                                       @RequestParam("plateNumber") String platenumber,
                                       @RequestParam("paymentType") TransactionPaymentType transactionPaymentType,
                                       @RequestParam(value = "dateTime", required = false, defaultValue = "${date.now}") LocalDateTime dateTime) {
        MutableDouble moneyObject = new MutableDouble(money);
        Optional<ParkingSystemDTO> parkingSystem = parkingSystemService.getParkingSystemById(id);
        if (parkingSystem.isEmpty()) {
            throw new NotFoundException("Parking system with id: " + id + " is not found");
        }
        if (money < 0) {
            throw new InvalidArgument("You can not use a negative money value");
        }
        return parkingSystemService.getExitTime(dateTime, parkingSystem.get(), moneyObject, platenumber, transactionPaymentType);

    }

}


