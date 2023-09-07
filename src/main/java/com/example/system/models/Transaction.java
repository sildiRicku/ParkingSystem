package com.example.system.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDateTime;

@Entity
@Table(name = "Transactions")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int transactionId;
    @Enumerated(EnumType.STRING)
    @Column(name = "paymentType")
    private TransactionPaymentType transactionPaymentType;
    @Column(name = "plateNumber")
    private String plateNumber;
    @Column(name = "entryTime")
    private LocalDateTime entryTime;
    @Column(name = "estimatedExitTime")
    private LocalDateTime estimatedExitTime;
    @Column(name = "value")
    private double transactionValue;
    @Enumerated(EnumType.STRING)
    @Column(name = "transactionStatus")
    private TransactionStatus transactionStatus;

    @ManyToOne
    @JoinColumn(name = "parking_system_id", referencedColumnName = "systemId")
    @JsonIgnore
    private ParkingSystem parkingSystem;

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId=" + transactionId + "\n" +
                ", transactionPaymentType=" + transactionPaymentType + "\n" +
                ", plateNumber='" + plateNumber + '\'' + "\n" +
                ", entryTime=" + entryTime + "\n" +
                ", estimatedExitTime=" + estimatedExitTime + "\n" +
                ", transactionValue=" + transactionValue + "\n" +
                ", transactionStatus=" + transactionStatus + "\n" +
                '}';
    }
}
