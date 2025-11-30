package com.booknest.dto;

public class CharacterDto {
    private String name;
    private String description;
    private String collection;
    private String book;
    
    public CharacterDto() {}
    
    public CharacterDto(String name, String description, String collection, String book) {
        this.name = name;
        this.description = description;
        this.collection = collection;
        this.book = book;
    }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public String getCollection() { return collection; }
    public void setCollection(String collection) { this.collection = collection; }
    
    public String getBook() { return book; }
    public void setBook(String book) { this.book = book; }
}
