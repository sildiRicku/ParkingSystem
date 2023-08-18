package com.example.system.dto;

import com.example.system.models.Period;
import lombok.*;


import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RuleDTO {
    private int id;
    private String name;
    private String details;
    private double cost;
    private LocalTime startTime;
    private LocalTime endTime;
    private Period period;

}
