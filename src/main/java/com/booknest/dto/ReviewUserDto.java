package com.booknest.dto;

public class ReviewUserDto {
    private String username;
    private Long bookId;
    private String bookTitle;
    private String bookAuthor;
    private String bookGenre;
    private Integer rating;
    private String text;

    public ReviewUserDto() {}
    
    // Getters and Setters
    public String getUsername() { return username;}
    public void setUsername(String username){ this.username = username;}
    
    public Long getBookId() { return bookId;}
    public void setBookId(Long bookId) { this.bookId = bookId;}

    public String getBookTitle() {return bookTitle; }
    public void setBookTitle(String bookTitle) {this.bookTitle = bookTitle;}

    public String getBookAuthor() { return bookAuthor;}
    public void setBookAuthor(String bookAuthor) {this.bookAuthor = bookAuthor; }

    public String getBookGenre() {return bookGenre;}
    public void setBookGenre(String bookGenre) {this.bookGenre = bookAuthor; }
    
    public Integer getRating() { return rating;}
    public void setreting( Integer rating) { this.rating = rating;}

    public String getText() { return text;}
    public void setText(String text) { this.text = text;}
}
