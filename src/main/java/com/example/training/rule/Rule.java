package com.example.training.rule;

import com.example.training.period.Period;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Rule {


    @Id
    @SequenceGenerator(name = "SEQ_RuleID", sequenceName = "SEQ_RuleID", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_RuleID")
    private int id;
    private String name;
    private String details;
    private double cost;
    private String startTime;
    private String endTime;

    /*@OneToOne(mappedBy = "periodId")
    private Period period;*/


    public Rule(int id) {
        this.id = id;
    }

    public Rule() {
    }


    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Rule{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
