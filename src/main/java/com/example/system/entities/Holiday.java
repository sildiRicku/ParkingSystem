package com.example.system.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Holiday {
    @Id
    @SequenceGenerator(name = "seq_holidayId", sequenceName = "seq_holidayId", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_holidayId")

    private int holidayId;
    @Column(name = "holiday_name")
    private String holidayName;
    @Column(name = "holiday_date")
    @Temporal(TemporalType.DATE)
    private Date holidayDate;
}
