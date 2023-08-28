package com.example.system.repositories;

import com.example.system.models.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepo extends JpaRepository<Transactions, Integer> {
}
