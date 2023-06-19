package com.example.system.services;


import com.example.system.dto.TransactionDTO;
import com.example.system.models.Transactions;
import com.example.system.repositories.TransactionsRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionsService {
    private final TransactionsRepo transactionsRepo;
    private final ModelMapper modelMapper;

    @Autowired
    public TransactionsService(TransactionsRepo transactionsRepo, ModelMapper modelMapper) {
        this.transactionsRepo = transactionsRepo;
        this.modelMapper = modelMapper;
    }

    public List<TransactionDTO> getAllTransactions() {
        List<Transactions> transactionsList = transactionsRepo.findAll();
        List<TransactionDTO> transactionDTOS = new ArrayList<>();
        for (Transactions transactions : transactionsList) {
            TransactionDTO transactionDTO = modelMapper.map(transactions, TransactionDTO.class);
            transactionDTOS.add(transactionDTO);
        }
        return transactionDTOS;
    }

    public Optional<TransactionDTO> getTransactionsById(int id) {
        Optional<Transactions> transactions = transactionsRepo.findById(id);
        if (transactions.isPresent()) {
            TransactionDTO transactionDTO = modelMapper.map(transactions.get(), TransactionDTO.class);
            return Optional.of(transactionDTO);
        } else {
            return Optional.empty();
        }
    }

    public TransactionDTO addTransactions(TransactionDTO transactionDTO) {
        Transactions transaction = modelMapper.map(transactionDTO, Transactions.class);
        transactionsRepo.save(transaction);
        transactionDTO = modelMapper.map(transaction, TransactionDTO.class);
        return transactionDTO;
    }
}
