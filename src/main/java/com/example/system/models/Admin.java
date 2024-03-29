package com.example.system.models;

import com.example.system.enums.EmailPreference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "fullName", nullable = false)
    private String fullName;
    @Valid
    @OneToOne
    @JoinColumn(name = "loginInfoId", referencedColumnName = "id")
    private UserCredentials loginInfo;
    @Enumerated(EnumType.STRING)
    private EmailPreference emailPreference;
    @OneToMany(mappedBy = "admin")
    @JsonIgnoreProperties
    private List<ParkingSystem> parkingSystems;
}

