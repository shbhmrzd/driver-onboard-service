package com.example.driveronboardservice.repository;

import com.example.driveronboardservice.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {
    // add any custom query methods here

    Optional<Driver> findByUsername(String username);
}