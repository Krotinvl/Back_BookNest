package com.booknest.dto;

public class UpdateReviewDto {
    private Integer rating;
    private String text;
    
    public UpdateReviewDto() {}
    
    public UpdateReviewDto(Integer rating, String text) {
        this.rating = rating;
        this.text = text;
    }
    
    public Integer getRating() { return rating; }
    public void setRating(Integer rating) { this.rating = rating; }
    
    public String getText() { return text; }
    public void setText(String text) { this.text = text; }
}
