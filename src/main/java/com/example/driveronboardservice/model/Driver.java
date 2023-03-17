package com.example.driveronboardservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Driver")
public class Driver {
    @Id
    private Long id;

    @Column(name="username", unique=true)
    private String username;

    private String name;

    @Column(name="phone", unique=true)
    private String phone;

    @Column(name="email", unique=true)
    private String email;

    private String address;

    private boolean isEligibleForRides = false;

    @OneToOne
    @JoinColumn(name = "fk_license_id")
    private Document license;

    @OneToOne
    @JoinColumn(name = "fk_tracking_id")
    private TrackingDevice trackingDevice;

    @OneToOne
    @JoinColumn(name = "fk_background_id")
    private BackgroundCheck backgroundCheck;

    private String vehicleMake;
    private String vehicleModel;
    private String vehicleYear;


    // Constructors, getters, and setters
}