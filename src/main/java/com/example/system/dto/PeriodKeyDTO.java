package com.example.system.dto;

import jakarta.persistence.Column;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PeriodKeyDTO {
    private int periodId;
    private int dayNumber;
}
