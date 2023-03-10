package com.example.system.dto;

import com.example.system.entities.PeriodKey;
import com.example.system.entities.Rule;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PeriodDTO {
    private PeriodKey periodId;
    private Rule rule;
}