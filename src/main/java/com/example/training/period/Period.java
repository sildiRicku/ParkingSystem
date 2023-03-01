package com.example.training.period;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "period")
public class Period {
    @EmbeddedId
    private PeriodId perioId;

}
