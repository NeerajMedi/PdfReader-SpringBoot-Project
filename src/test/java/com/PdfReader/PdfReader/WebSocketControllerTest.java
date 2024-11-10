package com.PdfReader.PdfReader;

import com.PdfReader.PdfReader.Dto.QuestionMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.simp.stomp.*;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import java.lang.reflect.Type;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class WebSocketControllerTest {

    @Value("${local.server.port}") 
    private int port;

    private WebSocketStompClient stompClient;
    private CompletableFuture<String> completableFuture;

    @BeforeEach
    public void setup() {
        this.stompClient = new WebSocketStompClient(new StandardWebSocketClient());
        this.stompClient.setAutoStartup(true); // Ensures Stomp headers auto-configure
        this.completableFuture = new CompletableFuture<>();
    
    try {
        Thread.sleep(5000); } 
    catch (InterruptedException e) {
        Thread.currentThread().interrupt();
    }
    }
    @Test
    public void testWebSocketConnection() throws Exception {
        // Prepare WebSocket connection URL
        String url = "ws://localhost:" + port + "/websocket-endpoint";

        // Connect asynchronously using connectAsync (avoids deprecated connect method)
        stompClient.connectAsync(url, new WebSocketHttpHeaders(), new StompSessionHandlerAdapter() {
            @Override
            public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
                System.out.println("WebSocket connection established");

                // Subscribe to the correct topic
                session.subscribe("/topic/answers", new StompFrameHandler() {
                    @Override
                    public Type getPayloadType(StompHeaders headers) {
                        return String.class;
                    }

                    @Override
                    public void handleFrame(StompHeaders headers, Object payload) {
                        System.out.println("Received message: " + payload); // Log the received message
                        completableFuture.complete((String) payload); // Complete the future with the received message
                    }
                });
                QuestionMessage message = new QuestionMessage();
                message.setQuestion("Test question");
                message.setDocumentId(1L);// Ensure the correct format
            
                // Send a test message to the server
                session.send("/app/questions",message);
                }
        });

        // Wait for the response and validate (increased timeout to 30 seconds)
        String message = completableFuture.get(60, TimeUnit.SECONDS);
        assertEquals("Processed answer for: Test question", message); // Adjust expected message based on server logic
    }

}
