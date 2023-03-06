package com.example.system.entities;

import jakarta.persistence.Column;

import java.io.Serializable;

public class PeriodKey implements Serializable {

    @Column(name = "period_id")
    private int periodId;
    @Column(name = "day_number")
    private int dayNumber;
}
