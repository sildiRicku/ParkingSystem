package com.example.system.services;

import com.example.system.models.Transactions;
import com.example.system.repositories.TransactionRepo;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TransactionService {
    private final TransactionRepo transactionRepo;
    private ModelMapper modelMapper;


    public Transactions saveTransaction(Transactions transaction) {
        return transactionRepo.save(transaction);
    }
}
