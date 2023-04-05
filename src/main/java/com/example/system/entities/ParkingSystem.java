package com.example.system.entities;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "parking_system")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class ParkingSystem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int systemId;

    @Column(name = "unique_identifier", unique = true, length = 12, nullable = false)
    private String identifier;

    @Column(name = "address")
    private String address;
    @Enumerated(EnumType.STRING)
    @Column(name = "working_status")
    private workingStatus workingStatus;

    @Temporal(TemporalType.DATE)
    @Column(name = "first_install_date")
    private Date firstInstallDate;

    @Column(name = "last_update")
    private String lastUpdate;

    @Column(name = "total_money")
    private double totalMoney;

    @Column(name = "firmware_version")
    private String firmwareVersion;

    @OneToMany(mappedBy = "parkingSystem")
    @JsonIgnoreProperties
    private List<Transactions> transactions;

    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "parking_system_rule",
            joinColumns = {@JoinColumn(name = "parking_system_id")},
            inverseJoinColumns = {@JoinColumn(name = "rule_id")}
    )

    private List<Rule> rules;

    public enum workingStatus {
        WORKING,
        FAIL,
        MONEY_FULL,
        NO_PAPER
    }
}
