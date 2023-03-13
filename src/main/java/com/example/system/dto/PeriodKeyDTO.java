package com.example.system.dto;

import lombok.*;

import java.time.DayOfWeek;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PeriodKeyDTO {
    private int periodId;
    private DayOfWeek dayNumber;
}
