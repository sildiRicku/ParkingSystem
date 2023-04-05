package com.example.system.entities;

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
    @SequenceGenerator(name = "seq_holidayId", sequenceName = "seq_holidayId", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_holidayId")

    private int holidayId;
    @Column(name = "holiday_name")
    private String holidayName;
    @Column(name = "holiday_date")
    @Temporal(TemporalType.DATE)
    private Date holidayDate;
}
