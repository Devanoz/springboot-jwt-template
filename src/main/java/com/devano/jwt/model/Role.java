package com.devano.jwt.model;


import jakarta.persistence.*;

@Entity
@Table(name = "user")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;

}
