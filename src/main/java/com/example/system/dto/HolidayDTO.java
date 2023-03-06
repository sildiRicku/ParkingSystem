package com.example.system.dto;

import lombok.*;


import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class HolidayDTO {

    private int holidayId;
    private String holidayName;
    private Date holidayDate;
}
