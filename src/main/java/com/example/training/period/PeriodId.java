package com.example.training.period;

import jakarta.persistence.Column;

import java.io.Serializable;

public class PeriodId implements Serializable {


    @Column(name = "period_id")
    private int periodId;
    @Column(name = "day_number")
    private int dayNumber;
}
