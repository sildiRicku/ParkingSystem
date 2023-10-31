package com.example.system.models;

import com.example.system.enums.EmailPreference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
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
    //    @Column(name = "email", nullable = false, unique = true)
//    @Email(message = "Bad Email Format")
//    private String email;
    @Valid
    @OneToOne
    @JoinColumn(name = "loginInfoId", referencedColumnName = "id")
    private UserLoginInfo loginInfo;
    @Enumerated(EnumType.STRING)
    private EmailPreference emailPreference;
    @OneToMany(mappedBy = "admin")
    @JsonIgnoreProperties
    private List<ParkingSystem> parkingSystems;
}

