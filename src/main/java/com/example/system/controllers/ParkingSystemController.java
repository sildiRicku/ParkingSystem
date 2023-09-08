package com.example.system.controllers;

import com.example.system.dto.RuleDTO;
import com.example.system.dto.TransactionDTO;
import com.example.system.models.ParkingSystem;
import com.example.system.models.Transaction;
import com.example.system.models.TransactionPaymentType;
import com.example.system.exceptionhandlers.InvalidArgument;
import com.example.system.exceptionhandlers.NotFoundException;
import com.example.system.helperclasses.MutableDouble;
import com.example.system.dto.ParkingSystemDTO;
import com.example.system.helperclasses.ParkingResponse;
import com.example.system.services.ParkingSystemService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/parking-system")
@CrossOrigin
public class ParkingSystemController {

    private final ParkingSystemService parkingSystemService;
    private final ModelMapper modelMapper;


    @Autowired
    public ParkingSystemController(ParkingSystemService parkingSystemService, ModelMapper modelMapper) {
        this.parkingSystemService = parkingSystemService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ParkingSystemDTO getParkingSystemById(@PathVariable int id) {
        Optional<ParkingSystemDTO> parkingSystemDTO = parkingSystemService.getParkingSystemById(id);
        if (parkingSystemDTO.isPresent()) {
            return parkingSystemDTO.get();
        }
        throw new NotFoundException("Parking system with this ID not found");

    }

    @GetMapping("/all")
    public List<ParkingSystemDTO> getAllParkingSystems() {
        return parkingSystemService.getAllParkingSystems();
    }

    @GetMapping("/parking-systems/{id}/rules")
    public ResponseEntity<List<RuleDTO>> getRulesForParkingSystem(@PathVariable int id) {
        List<RuleDTO> rules = parkingSystemService.getRulesForParkingSystem(id);
        return ResponseEntity.ok(rules);
    }

    @GetMapping("/parking-time")
    public ParkingResponse getExitTime(@RequestParam("money") double money, @RequestParam("id") int id, @RequestParam("plateNumber") String platenumber, @RequestParam("paymentType") TransactionPaymentType transactionPaymentType, @RequestParam(value = "dateTime", required = false, defaultValue = "${date.now}") LocalDateTime dateTime) {
        MutableDouble moneyObject = new MutableDouble(money);
        Optional<ParkingSystemDTO> parkingSystem = parkingSystemService.getParkingSystemById(id);
        if (!parkingSystem.isPresent()) {
            throw new NotFoundException("No parking system found");
        }
        if (money < 0) {
            throw new InvalidArgument("You can not use a negative money value");
        }
        return parkingSystemService.getExitTime(dateTime, parkingSystem.get(), moneyObject, platenumber, transactionPaymentType);

    }

    @PostMapping("/addTrans")
    public ResponseEntity<TransactionDTO> addTransaction(@RequestParam int parkingSystemId, @RequestBody TransactionDTO transactionDTO) {
        ParkingSystemDTO parkingSystemDTO = getParkingSystemById(parkingSystemId);
        ParkingSystem parkingSystem = modelMapper.map(parkingSystemDTO, ParkingSystem.class);
        TransactionDTO savedTransaction = parkingSystemService.saveTransactionForParkingSystem(parkingSystem, transactionDTO);
        return ResponseEntity.ok(savedTransaction);
    }

    @GetMapping("/getTrans")
    public List<Transaction> getAllTransactionsForParkingSystem(int id) {
        Optional<ParkingSystemDTO> parkingSystem = parkingSystemService.getParkingSystemById(id);
        if (parkingSystem.isPresent()) {
            return parkingSystem.get().getTransactions();
        } else throw new NotFoundException("Parking System with this Id is not available");
    }
}