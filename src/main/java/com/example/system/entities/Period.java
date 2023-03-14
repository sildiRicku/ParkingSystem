package com.example.system.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private PeriodKey periodKey;

    @ManyToOne
    @JoinColumn(name = "rule_applied_id", referencedColumnName = "ruleId")
    @JsonIgnore
    private Rule rule;
}
