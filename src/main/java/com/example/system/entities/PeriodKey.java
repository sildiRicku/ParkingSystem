package com.example.system.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PeriodKey periodKey = (PeriodKey) o;
        return periodId == periodKey.periodId && dayOfWeek == periodKey.dayOfWeek;
    }

    @Override
    public int hashCode() {
        return Objects.hash(periodId, dayOfWeek);

    }

}