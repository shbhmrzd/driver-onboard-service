package com.example.driveronboardservice.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "TrackingDevice")
public class TrackingDevice {
    @Id
    private Long id;
    private String serialNumber;
    private String model;
    private String carrier;
    private String trackingNumber;
    private String status;

    @OneToOne(mappedBy = "trackingDevice")
    private Driver driver;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Constructors, getters, and setters
}
