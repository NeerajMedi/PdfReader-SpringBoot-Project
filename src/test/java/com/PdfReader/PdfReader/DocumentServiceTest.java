package com.PdfReader.PdfReader;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.PdfReader.PdfReader.model.Document;
import com.PdfReader.PdfReader.repository.DocumentRepository;
import com.PdfReader.PdfReader.service.DocumentService;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
public class DocumentServiceTest {

    @Mock
    private DocumentRepository documentRepository;

    @InjectMocks
    private DocumentService documentService;

    @Test
    public void testFindDocumentById() {
        Document doc = new Document(1L, "Test Document");
        when(documentRepository.findById(1L)).thenReturn(Optional.of(doc));

        Document found = documentService.findDocumentById(1L);
        assertEquals(doc, found);
    }
}
