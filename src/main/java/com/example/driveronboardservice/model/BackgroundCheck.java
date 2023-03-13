package com.example.driveronboardservice.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Data
@Entity
@Table(name="BackgroundCheck")
public class BackgroundCheck {
    @Id
    private Long id;
    private boolean passed;
    private String comments;

    @OneToOne(mappedBy = "backgroundCheck")
    private Driver driver;

    // Constructors, getters, and setters
}

