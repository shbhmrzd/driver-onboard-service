package com.example.driveronboardservice.service;


import com.example.driveronboardservice.exception.DataNotFoundException;
import com.example.driveronboardservice.model.Document;
import com.example.driveronboardservice.model.Driver;
import com.example.driveronboardservice.repository.DocumentRepository;
import com.example.driveronboardservice.repository.DriverRepository;
import org.springframework.stereotype.Service;

@Service
public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepository documentRepository;

    public DocumentServiceImpl(DocumentRepository documentRepository) {
        super();
        this.documentRepository = documentRepository;
    }

    @Override
    public Document uploadDocument(Document document) {
        return documentRepository.save(document);
    }

    @Override
    public void deleteDocument(long id) {
        Document document = documentRepository.findById(id).orElse(null);
        if(document != null){
            documentRepository.delete(document);
        }
    }

    @Override
    public Document getDocumentById(long id) {
        Document document =  documentRepository.findById(id).orElse(null);
        if(document == null){
            throw new DataNotFoundException("Document does not exist for id : " + id);
        }
        return document;

    }
}
