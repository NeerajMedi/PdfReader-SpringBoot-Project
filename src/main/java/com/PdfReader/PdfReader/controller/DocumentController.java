package com.PdfReader.PdfReader.controller;


import com.PdfReader.PdfReader.model.Document;
import com.PdfReader.PdfReader.service.DocumentService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/documents")
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    @PostMapping("/upload")
    public ResponseEntity<Document> uploadPDF(@RequestParam MultipartFile file) {
        Document document = documentService.savePDF(file);
        return ResponseEntity.ok(document);}

    @GetMapping
    public ResponseEntity<List<Document>> getAllDocuments() {
        List<Document> documents = documentService.getAllDocuments();
        return ResponseEntity.ok(documents);
    }
}
