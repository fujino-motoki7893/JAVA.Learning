package com.learning.learning.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class TodoItemCreateRequest {
    
    @NotBlank(message = "Title is required")
    @Size(max = 100, message = "Title must be less than 100 characters")
    private String title;
    
    @Size(max = 500, message = "Description must be less than 500 characters")
    private String description;
    
    // Constructors
    public TodoItemCreateRequest() {}
    
    public TodoItemCreateRequest(String title, String description) {
        this.title = title;
        this.description = description;
    }
    
    // Getters and Setters
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
