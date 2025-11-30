package com.booknest.dto;

public class CreateReviewDto {
    private Long bookId;
    private Integer rating;
    private String text;
    
    public CreateReviewDto() {}
    
    public CreateReviewDto(Long bookId, Integer rating, String text) {
        this.bookId = bookId;
        this.rating = rating;
        this.text = text;
    }
    
    public Long getBookId() { return bookId; }
    public void setBookId(Long bookId) { this.bookId = bookId; }
    
    public Integer getRating() { return rating; }
    public void setRating(Integer rating) { this.rating = rating; }
    
    public String getText() { return text; }
    public void setText(String text) { this.text = text; }
}
