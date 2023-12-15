package com.example.system.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "Holiday")
@NoArgsConstructor
@AllArgsConstructor
public class Holiday {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int holidayId;
    @Column(name = "holiday_name")
    private String holidayName;
    @Column(name = "holiday_date")
    @Temporal(TemporalType.DATE)
    private Date holidayDate;
}
