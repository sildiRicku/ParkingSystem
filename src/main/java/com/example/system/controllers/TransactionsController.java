package com.example.system.controllers;

import com.example.system.dto.TransactionDTO;
import com.example.system.entities.Transactions;
import com.example.system.services.TransactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return transactionsService.getTransactionsById(id).orElse(null);
    }

    @PostMapping("/new")
    public Transactions addTransactions(@RequestBody TransactionDTO transactionDTO) {
        return transactionsService.addTransactions(transactionDTO);
    }
}
