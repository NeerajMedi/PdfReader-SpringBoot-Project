package com.PdfReader.PdfReader.service;

import java.util.List;
import com.PdfReader.PdfReader.model.Document;
import com.PdfReader.PdfReader.repository.DocumentRepository;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class DocumentService {

    @Autowired
    private DocumentRepository documentRepository;

    public Document savePDF(MultipartFile file) {
        String text = extractTextFromPDF(file);
        Document document = new Document();
        document.setFilename(file.getOriginalFilename());
        document.setTextContent(text);
        document.setUploadDate(LocalDateTime.now());
        return documentRepository.save(document);
    }

    private String extractTextFromPDF(MultipartFile file) {
        try (PDDocument document = PDDocument.load(file.getInputStream())) {
            PDFTextStripper pdfStripper = new PDFTextStripper();
            return pdfStripper.getText(document);
        } catch (IOException e) {
            throw new RuntimeException("Error reading PDF file", e);
        }
    }

	public Document findDocumentById(long L) {
		Optional<Document> document = documentRepository.findById(L);
        return document.orElseThrow(() -> new RuntimeException("Document not found"));
    
	}

	public List<Document> getAllDocuments() {
        return documentRepository.findAll();  // Assumes you have a method like this in the repository
    }

}
