package com.example.system.models;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
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
    @Column(name = "name", nullable = false, unique = true)
    private String name;
    @Column(name = "details")
    private String details;
    @Column(name = "cost")
    private double cost;
    @Column(name = "start_time")
    private LocalTime startTime;

    @Column(name = "end_time")
    private LocalTime endTime;

    @OneToMany(mappedBy = "rule")
    @JsonIgnore
    private List<Period> periods;

    public Rule(double cost, LocalTime startTime, LocalTime endTime) {
        this.cost = cost;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "Rule{" +
                "ruleId=" + ruleId + "\n" +
                ", name='" + name + '\'' + "\n" +
                ", details='" + details + '\'' + "\n" +
                ", cost=" + cost + "\n" +
                ", startTime=" + startTime + "\n" +
                ", endTime=" + endTime + "\n" +
                '}';
    }
}
