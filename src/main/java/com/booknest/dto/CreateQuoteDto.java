package com.booknest.dto;

public class CreateQuoteDto {
    private String text;
    private String book;
    private String character;
    
    public CreateQuoteDto() {}
    
    public CreateQuoteDto(String text, String book, String character) {
        this.text = text;
        this.book = book;
        this.character = character;
    }
    
    public String getText() { return text; }
    public void setText(String text) { this.text = text; }
    
    public String getBook() { return book; }
    public void setBook(String book) { this.book = book; }
    
    public String getCharacter() { return character; }
    public void setCharacter(String character) { this.character = character; }
}
