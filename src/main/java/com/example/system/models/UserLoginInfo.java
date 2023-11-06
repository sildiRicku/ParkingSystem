package com.example.system.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "userLoginInfo")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;
    @Column(unique = true)
    @Email(message = "Bad Email Format")
    private String email;
    @Column
    private String password;
    @OneToOne(mappedBy = "loginInfo")
    private Admin admin;

}
