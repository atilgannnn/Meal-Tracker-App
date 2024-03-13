package com.mealtracker.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50,nullable = false)
    private String username;
    @Column(length = 80,nullable = false, unique = true)
    private String email;
    @Column(length = 120,nullable = false)
    private String password;
    @Column(nullable = false)
    private Boolean builtIn = false;
    @Column
    private int role;

}
