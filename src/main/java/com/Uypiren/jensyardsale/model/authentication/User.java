package com.Uypiren.jensyardsale.model.authentication;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "username",nullable = false)
    private String userName;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false)
    private String role;

    public User(String userName, String password, String role, String fullName) {
        this.userName = userName;
        this.password =password;
       // this.role =  role;
        this.fullName =  fullName;
        this.role ="Admin";
    }
}