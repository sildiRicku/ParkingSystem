package com.example.system.dto;

import com.example.system.models.ParkingSystem.workingStatus;

import com.example.system.models.Rule;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class ParkingSystemDTO {
    private int id;

    private String address;

    private workingStatus workingStatus;

    private Date firstInstallDate;

    private String lastUpdate;

    private double totalMoney;

    private String firmwareVersion;
    @JsonIgnore
    private List<Rule> rules;
}
