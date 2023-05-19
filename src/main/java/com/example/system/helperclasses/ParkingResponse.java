package com.example.system.helperclasses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ParkingResponse {
    private String plateNumber;
    private String exitTime;


}
