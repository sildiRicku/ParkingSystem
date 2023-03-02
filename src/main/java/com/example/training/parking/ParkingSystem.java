package com.example.training.parking;

import java.util.Date;
import java.util.List;

import jakarta.persistence.*;

import com.example.training.rule.Rule;
import com.example.training.transaction.Transactions;

@Entity
@Table(name = "parking_system")
public class ParkingSystem {

    @Id
    @SequenceGenerator(name = "SEQ_Parking_ID", sequenceName = "SEQ_Parking_ID", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_Parking_ID")
    private int id;

    @Column(name = "address")
    private String address;

    @Column(name = "working_status")
    private String workingStatus;

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
    private List<Transactions> transactions;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "parking_system_rule",
            joinColumns = {@JoinColumn(name = "parking_system_id")},
            inverseJoinColumns = {@JoinColumn(name = "rule_id")}
    )
    private List<Rule> rules;


    public ParkingSystem() {

    }

    public List<Transactions> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transactions> transactions) {
        this.transactions = transactions;
    }

    public List<Rule> getRules() {
        return rules;
    }

    public void setRules(List<Rule> rules) {
        this.rules = rules;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWorkingStatus() {
        return workingStatus;
    }

    public void setWorkingStatus(String workingStatus) {
        this.workingStatus = workingStatus;
    }

    public Date getFirstInstallDate() {
        return firstInstallDate;
    }

    public void setFirstInstallDate(Date firstInstallDate) {
        this.firstInstallDate = firstInstallDate;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getFirmwareVersion() {
        return firmwareVersion;
    }

    public void setFirmwareVersion(String firmwareVersion) {
        this.firmwareVersion = firmwareVersion;
    }


    @Override
    public String toString() {
        return "ParkingSystem{" +
                "id='" + id + '\'' +
                ", address='" + address + '\'' +
                ", workingStatus='" + workingStatus + '\'' +
                ", firstInstallDate=" + firstInstallDate +
                ", lastUpdate='" + lastUpdate + '\'' +
                ", totalMoney=" + totalMoney +
                ", firmwareVersion='" + firmwareVersion + '\'' +
                '}';
    }
}
