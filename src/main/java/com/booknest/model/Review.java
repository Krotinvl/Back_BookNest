package com.booknest.model;

import jakarta.persistence.*;

@Entity
@Table(name = "reviews")
@IdClass(ReviewId.class)
public class Review {
    @Id
    @ManyToOne
    @JoinColumn(name = "id_book")
    private Book book;
    
    @Id
    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;
    
    private Integer rating;
    
    @Column(length = 1000)
    private String text;
    
    public Review() {}
    
    // Getters and Setters
    public Book getBook() { return book; }
    public void setBook(Book book) { this.book = book; }
    
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    
    public Integer getRating() { return rating; }
    public void setRating(Integer rating) { this.rating = rating; }
    
    public String getText() { return text; }
    public void setText(String text) { this.text = text; }
}


