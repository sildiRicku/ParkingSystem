package com.example.system.dto;

import com.example.system.models.PeriodKey;
import com.example.system.models.Rule;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PeriodDTO {
    private PeriodKey periodId;
    private Rule rule;
}
