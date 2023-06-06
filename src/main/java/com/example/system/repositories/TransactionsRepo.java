package com.example.system.repositories;

import com.example.system.models.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionsRepo extends JpaRepository<Transactions, Integer> {
}
