package com.PdfReader.PdfReader.repository;

import com.PdfReader.PdfReader.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<Document, Long> {
}
