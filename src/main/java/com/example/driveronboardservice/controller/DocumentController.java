package com.example.driveronboardservice.controller;

import com.example.driveronboardservice.model.*;
import com.example.driveronboardservice.service.DocumentService;
import com.example.driveronboardservice.service.DriverService;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/document")
public class DocumentController {

    private DocumentService documentService;
    private DriverService driverService;

    public DocumentController(DocumentService documentService, DriverService driverService) {
        super();
        this.documentService = documentService;
        this.driverService = driverService;
    }

    @PostMapping(value = "/upload", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_OCTET_STREAM_VALUE})
    @ApiOperation(value = "Upload a document")
    public ResponseEntity<DocumentUploadResponse> upload(@RequestParam(value = "file") MultipartFile file, @RequestParam(value = "driverId") Long driverId)
            throws IOException{

        byte [] byteArr=file.getBytes();
        Driver driver = driverService.getDriverById(driverId);
        Document document = new Document();
        document.setDriver(driver);
        document.setFile(byteArr);
        document.setType("License");
        Document documentCreated = documentService.uploadDocument(document);
        driver.setLicense(documentCreated);
        driverService.updateDriver(driverId, driver);

        DocumentUploadResponse  documentUploadResponse = new DocumentUploadResponse();
        documentUploadResponse.setId(documentCreated.getId());
        documentUploadResponse.setStatus("Uploaded");
        return new ResponseEntity<DocumentUploadResponse>(documentUploadResponse,  HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get a document by ID")
    public ResponseEntity<Resource> getDocumentById(@PathVariable(value = "id") Long id) {

        Document document = documentService.getDocumentById(id);
        if(document == null){
            return ResponseEntity.notFound().build();
        }
        byte[] array = document.getFile();

        ByteArrayResource resource = new ByteArrayResource(array);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .contentLength(resource.contentLength())
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        ContentDisposition.attachment()
                                .filename("file"+id+".pdf")
                                .build().toString())
                .body(resource);
    }

}
