package com.example.driveronboardservice.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="BackgroundCheck")
public class BackgroundCheck {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String status;
    private String comments;

    @OneToOne(mappedBy = "backgroundCheck")
    private Driver driver;

    // Constructors, getters, and setters
}

