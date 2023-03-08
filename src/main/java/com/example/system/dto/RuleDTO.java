package com.example.system.dto;

import com.example.system.entities.Period;
import lombok.*;

import java.sql.Time;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class RuleDTO {
    private int id;
    private String name;
    private String details;
    private double cost;
    private Time startTime;
    private Time endTime;
    private Period period;
}
