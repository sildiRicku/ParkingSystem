package com.example.system.dto;

import com.example.system.entities.ParkingSystem.workingStatus;

import lombok.*;

import java.util.Date;

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

}
