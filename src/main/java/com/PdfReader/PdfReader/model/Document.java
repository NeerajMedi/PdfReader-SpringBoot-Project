package com.PdfReader.PdfReader.model;


import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String filename;
    private String textContent;
    private LocalDateTime uploadDate;
	
	  public Document(Long id, String filename) {
	        this.id = id;
	        this.filename = filename;
	    }
	public Document() {
		// TODO Auto-generated constructor stub
	}
	public LocalDateTime getUploadDate() {
		return uploadDate;
	}
	public void setUploadDate(LocalDateTime uploadDate) {
		this.uploadDate = uploadDate;
	}
	public String getTextContent() {
		return textContent;
	}
	public void setTextContent(String textContent) {
		this.textContent = textContent;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}

    // Getters and setters
}
