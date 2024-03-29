package com.example.system.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Data;


@Entity
@Table(name = "userLoginInfo")
@Data
public class UserCredentials {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;
    @Column(unique = true)
    @Email(message = "Bad Email Format")
    private String email;
    @Column
    private String password;
    @ManyToOne
    @JoinColumn(name = "authority_id",referencedColumnName = "id")
    private Authority authority;
    @OneToOne(mappedBy = "loginInfo")
    private Admin admin;
}
