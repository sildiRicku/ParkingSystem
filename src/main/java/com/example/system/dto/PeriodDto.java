package com.example.system.dto;

import com.example.system.entities.PeriodKey;
import com.example.system.entities.Rule;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PeriodDto {
    private PeriodKey periodId;
    private Rule rule;
}
