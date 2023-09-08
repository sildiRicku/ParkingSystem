package com.example.system.repositories;

import com.example.system.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransactionRepo extends JpaRepository<Transaction, Integer> {
    @Query(value = "SELECT * FROM transactions t WHERE t.parking_system_id = :parkingSystemId AND t.entry_time >= DATE_SUB(NOW(), INTERVAL 24 HOUR)", nativeQuery = true)
    List<Transaction> findTransactionsInLast24HoursForParkingSystem(@Param("parkingSystemId") int parkingSystemId);

}
