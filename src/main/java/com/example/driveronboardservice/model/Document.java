package com.example.driveronboardservice.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Document")
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String type;
    private byte[] file;
    private String status;

    @OneToOne(mappedBy = "license")
    private Driver driver;

    // Constructors, getters, and setters
}