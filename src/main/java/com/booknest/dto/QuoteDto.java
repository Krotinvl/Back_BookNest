package com.booknest.dto;

public class QuoteDto {
    private Long id;
    private String text;
    private String book;
    private String character;
    
    public QuoteDto() {}
    
    public QuoteDto(Long id, String text, String book, String character) {
        this.id = id;
        this.text = text;
        this.book = book;
        this.character = character;
    }
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getText() { return text; }
    public void setText(String text) { this.text = text; }
    
    public String getBook() { return book; }
    public void setBook(String book) { this.book = book; }
    
    public String getCharacter() { return character; }
    public void setCharacter(String character) { this.character = character; }
}