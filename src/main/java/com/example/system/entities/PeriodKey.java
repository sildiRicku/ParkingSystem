package com.example.system.entities;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PeriodKey implements Serializable {

    @Column(name = "period_id")
    private int periodId;
    @Column(name = "day_number")
    private int dayNumber;
}
