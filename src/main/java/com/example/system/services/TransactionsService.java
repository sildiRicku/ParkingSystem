package com.example.system.services;

//import com.example.system.dto.TransactionDTO;
//import com.example.system.entities.Rule;

import com.example.system.entities.Transactions;
import com.example.system.repositories.TransactionsRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionsService {
    private final TransactionsRepo transactionsRepo;

    @Autowired
    public TransactionsService(TransactionsRepo transactionsRepo) {
        this.transactionsRepo = transactionsRepo;
    }

    public List<Transactions> getAllTransactions() {
        return transactionsRepo.findAll();
    }

    public Optional<Transactions> getTransactionsById(int id) {
        return transactionsRepo.findById(id);
    }

    public Transactions addTransactions(Transactions transactions) {
//        Transactions transactions2 = processTransaction(transactions, ruleService.getRuleById(1).get());
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
