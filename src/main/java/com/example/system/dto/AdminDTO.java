package com.example.system.dto;

import com.example.system.models.ParkingSystem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AdminDTO {
    private int id;

    private String fullName;
    private String email;
    private List<ParkingSystem> parkingSystems;
}
