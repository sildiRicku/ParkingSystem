package com.example.system.dto;

import lombok.*;


import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HolidayDTO {

    private int holidayId;
    private Date holidayDate;
}
