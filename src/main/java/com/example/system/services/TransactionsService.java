package com.example.system.services;

import com.example.system.entities.Transactions;
import com.example.system.repositories.TransactionsRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionsService {
    private final TransactionsRepo transactionsRepo;

    public TransactionsService(TransactionsRepo transactionsRepo) {
        this.transactionsRepo = transactionsRepo;
    }

    public List<Transactions> getAllTransactions() {
        return transactionsRepo.findAll();
    }

    public Optional<Transactions> getTransactionsById(int id) {
        return transactionsRepo.findById(id);
    }

}
