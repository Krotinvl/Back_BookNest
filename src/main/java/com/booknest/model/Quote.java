package com.booknest.model;

import jakarta.persistence.*;

@Entity
@Table(name = "quotes")
public class Quote {
    @Id
    @Column(name = "id_quote")
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "id_user" , referencedColumnName = "username")
    private User user;
    
    @Column(length = 500)
    private String text;
    
    private String book;
    
    private String character;
    
    public Quote() {}
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    
    public String getText() { return text; }
    public void setText(String text) { this.text = text; }
    
    public String getBook() { return book; }
    public void setBook(String book) { this.book = book; }
    
    public String getCharacter() { return character; }
    public void setCharacter(String character) { this.character = character; }
}