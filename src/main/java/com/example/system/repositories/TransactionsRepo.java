package com.example.system.repositories;

import com.example.system.entities.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionsRepo extends JpaRepository<Transactions, Integer> {
}
