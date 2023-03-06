package com.example.system.dto;

import com.example.system.entities.Period;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RuleDTO {
    private int id;
    private String name;
    private String details;
    private double cost;
    private Time startTime;
    private Time endTime;
    private Period period;
}
