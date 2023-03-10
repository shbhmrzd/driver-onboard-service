package com.example.driveronboardservice.repository;

import com.example.driveronboardservice.model.BackgroundCheck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BackgroundCheckRepository extends JpaRepository<BackgroundCheck, Long> {
    // add any custom query methods here
}