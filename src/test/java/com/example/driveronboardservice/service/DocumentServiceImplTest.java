package com.example.driveronboardservice.service;

import com.example.driveronboardservice.model.Document;
import com.example.driveronboardservice.repository.DocumentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DocumentServiceImplTest {

    @Mock
    private DocumentRepository documentRepository;

    @Test
    public void testUploadDocument() {
        // Arrange
        Document document = new Document();
        when(documentRepository.save(document)).thenReturn(document);
        DocumentServiceImpl documentService = new DocumentServiceImpl(documentRepository);

        // Act
        Document result = documentService.uploadDocument(document);

        // Assert
        assertEquals(document, result);
        verify(documentRepository, times(1)).save(document);
    }

    @Test
    public void testDeleteDocument() {
        // Arrange
        long id = 1L;
        Document document = new Document();
        when(documentRepository.findById(id)).thenReturn(Optional.of(document));
        DocumentServiceImpl documentService = new DocumentServiceImpl(documentRepository);

        // Act
        documentService.deleteDocument(id);

        // Assert
        verify(documentRepository, times(1)).findById(id);
        verify(documentRepository, times(1)).delete(document);
    }

    @Test
    public void testGetDocumentByNonExistingId() {
        // Arrange
        long id = 1L;
        when(documentRepository.findById(id)).thenReturn(Optional.empty());
        DocumentServiceImpl documentService = new DocumentServiceImpl(documentRepository);

        // Act
        Document result = documentService.getDocumentById(id);

        // Assert
        assertNull(result);
        verify(documentRepository, times(1)).findById(id);
    }
}

