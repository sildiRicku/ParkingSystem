package com.example.system.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;

@Entity
@Table(name = "rule")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Rule {

    @Id
    @SequenceGenerator(name = "SEQ_RuleID", sequenceName = "SEQ_RuleID", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_RuleID")
    private int id;
    private String name;
    private String details;
    private double cost;
    private Time startTime;
    private Time endTime;

    @OneToOne(cascade = CascadeType.ALL)
    private Period period;

}
