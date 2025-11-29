package com.booknest.dto;

import jakarta.validation.constraints.NotBlank;

public class UpdateCollectionDto {
    
    @NotBlank(message = "Коллекция обязательна")
    private String collection; 
    
    public UpdateCollectionDto() {}
    
    // Конструктор
    public UpdateCollectionDto(String collection) {
        this.collection = collection;
    }
    
    // Getters and Setters
    public String getCollection() { return collection; }
    public void setCollection(String collection) { this.collection = collection; }
}