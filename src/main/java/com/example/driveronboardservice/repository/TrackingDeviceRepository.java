package com.example.driveronboardservice.repository;

import com.example.driveronboardservice.model.TrackingDevice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrackingDeviceRepository extends JpaRepository<TrackingDevice, Long> {
    // add any custom query methods here
}