package com.example.system.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "Transactions")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Transactions {
    @Id
    @SequenceGenerator(name = "SEQ_TransactionId", sequenceName = "SEQ_TransactionId", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TransactionId")
    private int transId;
    @Enumerated(EnumType.STRING)
    @Column(name = "paymentType")
    private PaymentType paymentType;
    @Column(name = "platenumber")
    private String plateNumber;
    @Temporal(TemporalType.DATE)
    @Column(name = "dateOfTransaction")
    private Date dateOfTransaction;
    @Column(name = "value")
    private double transactionValue;
    @Enumerated(EnumType.STRING)
    @Column(name = "transactionStatus")
    private TransactionStatus transactionStatus;
    @ManyToOne
    @JoinColumn(name = "parking_system_id")
    @JsonIgnore
    private ParkingSystem parkingSystem;


    public enum PaymentType {
        CASH,
        CARD,
        MOBILE_WALLET
    }

    public enum TransactionStatus {
        READY,
        PROCESSING,
        ERROR,
        SUCCESS,
        DONE
    }
}
