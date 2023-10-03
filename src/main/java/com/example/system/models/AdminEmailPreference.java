package com.example.system.models;

import com.example.system.enums.EmailPreference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdminEmailPreference {
    @Id
    private int preferenceId;
    @Enumerated(EnumType.STRING)
    @Column(name = "preference")
    private EmailPreference emailPreference;
    @OneToMany(mappedBy = "emailPreference")
    private List<Admin> admins;
}
