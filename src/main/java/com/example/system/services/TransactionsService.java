package com.example.system.services;


import com.example.system.dto.TransactionDTO;
import com.example.system.entities.Transactions;
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
        List<Transactions> transactionss = transactionsRepo.findAll();
        List<TransactionDTO> transactionDTOS = new ArrayList<>();
        for (Transactions transactions : transactionss) {
            TransactionDTO transactionDTO = modelMapper.map(transactions, TransactionDTO.class);
            transactionDTOS.add(transactionDTO);
        }
        return transactionDTOS;
    }

    public Optional<TransactionDTO> getTransactionsById(int id) {
        Optional<Transactions> transactions = transactionsRepo.findById(id);
        if (transactions.isPresent()) {
            TransactionDTO transactionDTO = modelMapper.map(transactions, TransactionDTO.class);
            return Optional.of(transactionDTO);
        } else {
            return Optional.empty();
        }
    }

    public Transactions addTransactions(TransactionDTO transactionDTO) {
//        Transactions transactions2 = processTransaction(transactions, ruleService.getRuleById(1).get());
        Transactions transactions = modelMapper.map(transactionDTO, Transactions.class);
        return transactionsRepo.save(transactions);
    }

//    public Transactions processTransaction(Transactions transactions, Rule rule) {
//        if (rule.getPeriod().getPeriodId().getDayOfWeek().getValue() == 6) {
//            transactions.setTransactionValue(0);
//        } else
//            transactions.setTransactionValue(rule.getCost());
//        return transactionsRepo.save(transactions);
//    }
}
