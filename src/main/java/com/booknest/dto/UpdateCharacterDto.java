package com.booknest.dto;

public class UpdateCharacterDto {
    private String description;
    private String collection;
    private String book;
    
    public UpdateCharacterDto() {}
    
    public UpdateCharacterDto(String description, String collection, String book) {
        this.description = description;
        this.collection = collection;
        this.book = book;
    }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public String getCollection() { return collection; }
    public void setCollection(String collection) { this.collection = collection; }
    
    public String getBook() { return book; }
    public void setBook(String book) { this.book = book; }
}
