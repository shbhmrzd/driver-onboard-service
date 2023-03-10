package com.example.driveronboardservice.repository;

import com.example.driveronboardservice.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {
    // add any custom query methods here
}
