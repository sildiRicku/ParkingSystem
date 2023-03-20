package com.example.system.controllers;

import com.example.system.dto.TransactionDTO;
import com.example.system.services.TransactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/transactions")
public class TransactionsController {
    private final TransactionsService transactionsService;

    @Autowired
    public TransactionsController(TransactionsService transactionsService) {
        this.transactionsService = transactionsService;
    }

    @GetMapping("/all")
    public List<TransactionDTO> getAllTransactions() {
        return transactionsService.getAllTransactions();
    }

    @GetMapping("/{id}")
    public TransactionDTO getTransactionsById(@PathVariable int id) {
        Optional<TransactionDTO> transactionDTO = transactionsService.getTransactionsById(id);
        if (transactionDTO.isPresent()) {
            return transactionDTO.get();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/new")
    public TransactionDTO addTransactions(@RequestBody TransactionDTO transactionDTO) {
        return transactionsService.addTransactions(transactionDTO);
    }
}
