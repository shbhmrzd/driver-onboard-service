package com.example.driveronboardservice.service;

import com.example.driveronboardservice.model.Document;
import com.example.driveronboardservice.model.Driver;

public interface DocumentService {

    Document uploadDocument(Document document);

    void deleteDocument(long id);

    Document getDocumentById(long id);
}
