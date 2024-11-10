package com.PdfReader.PdfReader;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import java.util.List;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;


import com.PdfReader.PdfReader.model.Document;
import com.PdfReader.PdfReader.service.DocumentService;

//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.boot.test.web.server.LocalServerPort;
//
//import com.PdfReader.PdfReader.model.Document;
//import com.PdfReader.PdfReader.service.DocumentService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class DocumentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DocumentService documentService;  // MockBean to mock service methods

    @Test
    public void testGetDocuments() throws Exception {
        // Create a list of documents to mock the service response
        List<Document> documents = Arrays.asList(
            new Document(1L, "Document 1"), 
            new Document(2L, "Document 2")
        );

        // Mock the service's getAllDocuments() method
        when(documentService.getAllDocuments()).thenReturn(documents);

        // Perform the GET request to /documents and check the response
        mockMvc.perform(get("/documents"))
               .andExpect(status().isOk())  // Expecting 200 OK
               .andExpect(jsonPath("$.length()").value(2));  // Verifying list size
    }
}
