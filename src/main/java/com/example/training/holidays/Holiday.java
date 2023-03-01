package com.example.training.holidays;

import jakarta.persistence.*;

@Entity
public class Holiday {
    @Id
    @SequenceGenerator(name = "seq_holidayId",sequenceName = "seq_holidayId",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_holidayId")

    private int holidayId;
    @Column(name = "holiday_name")
    private String holidayName;
    @Column(name = "holiday_date")
    private String holidayDate;
}
