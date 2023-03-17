package com.example.driveronboardservice.controller;

import com.example.driveronboardservice.exception.OperationNotPermitted;
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
    public ResponseEntity<DocumentUploadResponse> upload(@RequestParam(value = "file") MultipartFile file, @RequestParam(value = "username") String username) throws IOException {

        byte [] byteArr=file.getBytes();
        Driver driver = driverService.getDriverByUsername(username);
        Document document = new Document();
        document.setDriver(driver);
        document.setFile(byteArr);
        document.setType("License");
        Document documentCreated = documentService.uploadDocument(document);
        driver.setLicense(documentCreated);
        driverService.updateDriver(username, driver);

        DocumentUploadResponse  documentUploadResponse = new DocumentUploadResponse();
        documentUploadResponse.setId(documentCreated.getId());
        documentUploadResponse.setStatus("Uploaded");

        return new ResponseEntity<DocumentUploadResponse>(documentUploadResponse,  HttpStatus.CREATED);
    }

    @GetMapping("/{username}/{id}")
    @ApiOperation(value = "Get a document by ID")
    public ResponseEntity<Resource> getDocumentById(@PathVariable(value = "id") Long id, @PathVariable(value = "username") String username) {

        Driver driver = driverService.getDriverByUsername(username);
        if(driver.getLicense() == null || driver.getLicense().getId() != id){
            throw new OperationNotPermitted("No such document for the driver : " + username);
        }
        Document document = documentService.getDocumentById(id);

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
