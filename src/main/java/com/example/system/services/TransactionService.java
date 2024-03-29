package com.example.system.services;

import com.example.system.models.Transaction;
import com.example.system.repositories.TransactionRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TransactionService {
    private final TransactionRepo transactionRepo;


    public Transaction saveTransaction(Transaction transaction) {
        return transactionRepo.save(transaction);
    }
}
