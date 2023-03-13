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
    @SequenceGenerator(name = "SEQ_Parking_ID", sequenceName = "SEQ_Parking_ID", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_Parking_ID")
    private int id;

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

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name = "rule_id", referencedColumnName = "id")
    @JoinTable(
            name = "parking_system_rule",
            joinColumns = {@JoinColumn(name = "parking_system_id")},
            inverseJoinColumns = {@JoinColumn(name = "rule_id")}
    )
    @JsonIgnoreProperties
    private List<Rule> rules;

    public enum workingStatus {
        Working,
        Fail,
        Money_full,
        No_paper
    }
}
