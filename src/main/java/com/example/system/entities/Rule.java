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
    @Column(name = "name")
    private String name;
    @Column(name = "details")
    private String details;
    @Column(name = "cost")
    private double cost;
    @Column(name = "start_time")
    private Time startTime;
    @Column(name = "end_time")
    private Time endTime;

    @OneToOne(cascade = CascadeType.ALL)
    private Period period;

}
