package com.learning.learning.dto;

import jakarta.validation.constraints.Size;

public class TodoItemUpdateRequest {
    
    @Size(max = 100, message = "Title must be less than 100 characters")
    private String title;
    
    @Size(max = 500, message = "Description must be less than 500 characters")
    private String description;
    
    private Boolean completed;
    
    // Constructors
    public TodoItemUpdateRequest() {}
    
    // Getters and Setters
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public Boolean getCompleted() { return completed; }
    public void setCompleted(Boolean completed) { this.completed = completed; }
}
