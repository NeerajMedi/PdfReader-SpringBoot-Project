# PDF Reader WebSocket API

A Spring Boot-based API for uploading and processing PDF documents, utilizing WebSocket to handle real-time question-answering through Natural Language Processing (NLP). This project integrates with a front-end WebSocket client to receive user questions, process them, and return relevant answers based on the content of uploaded PDF documents.

## Features

- **PDF Upload**: Upload PDF files and extract text content using Apache PDFBox.
- **WebSocket Real-Time Communication**: Connect via WebSocket to send and receive messages in real-time.
- **Question-Answering Endpoint**: Ask questions about PDF content and receive answers, simulating NLP-powered processing.
- **REST API**: RESTful endpoint for uploading PDF files, storing metadata in a database.
- **Unit Testing**: Comprehensive unit tests for WebSocket communication and REST endpoints.

## Project Structure

- **Controller**:
  - `DocumentController`: Handles PDF file uploads.
  - `WebSocketController`: Processes questions through WebSocket and returns answers.
- **Service**:
  - `DocumentService`: Extracts text from uploaded PDFs and saves document metadata.
- **Model**:
  - `Document`: Represents PDF metadata with fields like `filename`, `textContent`, and `uploadDate`.
- **Repository**:
  - `DocumentRepository`: Interface for database operations on PDF metadata.
- **DTO**:
  - `QuestionMessage`: Defines the WebSocket message format for questions about documents.

## Dependencies

- **Spring Boot**: Core framework for creating REST and WebSocket APIs.
- **Spring WebSocket**: Enables WebSocket support.
- **Apache PDFBox**: PDF processing library for text extraction.
- **JUnit**: Unit testing framework.
- **Mockito**: For mocking dependencies in unit tests.

## Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/username/PdfReaderWebSocketAPI.git
   cd PdfReaderWebSocketAPI
   ```

2. Set up the database (e.g., H2 or MySQL) and configure it in `application.properties`.

3. Build and run the application:
   ```bash
   ./mvnw spring-boot:run
   ```

## Usage

- **Upload a PDF**: `POST /api/documents/upload` with `MultipartFile` for PDF.
- **WebSocket Questions**:
  - Connect to WebSocket endpoint `/websocket-endpoint`.
  - Subscribe to `/topic/answers` to receive answers.
  - Send questions to `/app/questions` with `QuestionMessage` format.

## Testing

Run unit tests:
```bash
./mvnw test
```

### Troubleshooting

- **Timeouts in WebSocket Tests**: Ensure endpoints match and try `CompletableFuture` for async response handling.
- **WebSocket Issues**: Check configurations in WebSocket-related endpoints and the `WebSocketControllerTest`.

## License

This project is licensed under the MIT License.
