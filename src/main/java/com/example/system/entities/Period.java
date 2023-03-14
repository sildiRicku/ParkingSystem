package com.example.system.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "period")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Period {
    @EmbeddedId
    private PeriodKey periodId;
    @OneToOne(mappedBy = "period")
    private Rule rule;

}
