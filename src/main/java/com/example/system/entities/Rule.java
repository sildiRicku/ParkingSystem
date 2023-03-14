package com.example.system.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
import java.util.List;

@Entity
@Table(name = "rule")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Rule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int ruleId;
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

    @OneToMany(mappedBy = "rule")
    @JsonIgnoreProperties
    private List<Period> periods;
}
