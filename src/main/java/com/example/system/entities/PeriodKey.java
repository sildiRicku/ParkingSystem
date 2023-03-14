package com.example.system.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.DayOfWeek;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Embeddable
public class PeriodKey implements Serializable {

    @Column(name = "period_id")
    private int periodId;

    @Column(name = "day_number")
    private DayOfWeek dayOfWeek;
}