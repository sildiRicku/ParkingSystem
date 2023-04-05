package com.example.system.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;


import java.util.Date;

@Entity
@Table(name = "Transactions")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Transactions {
    @Id
    @SequenceGenerator(name = "seq_transactionId", sequenceName = "seq_transactionId", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_transactionId")

    private int transId;
    @Enumerated(EnumType.STRING)
    @Column(name = "paymentType")
    private TransactionPaymentType transactionPaymentType;
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
    @JoinColumn(name = "parking_system_id", referencedColumnName = "systemId")
    @JsonIgnore
    private ParkingSystem parkingSystem;


}
