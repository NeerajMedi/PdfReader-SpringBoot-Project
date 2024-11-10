package com.PdfReader.PdfReader.controller;

import com.PdfReader.PdfReader.Dto.QuestionMessage;
import com.PdfReader.PdfReader.model.Document;
import com.PdfReader.PdfReader.repository.DocumentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

    @Autowired
    private DocumentRepository documentRepository;

    @MessageMapping("/questions")
    @SendTo("/topic/answers")
    public String handleQuestion(QuestionMessage message) {
        Document document = documentRepository.findById(message.getDocumentId())
                .orElseThrow(() -> new RuntimeException("Document not found"));
        
        // Here you can use the document's content to process the question. For simplicity, I'm returning part of the document text.
        String documentText = document.getTextContent();
        String answer = "Processed answer for: " + message.getQuestion() + "\nDocument content: " + documentText; // You can improve the answer logic here
        
        return answer;
    }
}
