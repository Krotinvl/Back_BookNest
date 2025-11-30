package com.booknest.model;

import jakarta.persistence.*;


@Entity
@Table(name = "characters")
@IdClass(CharacterId.class)
public class Character {
    @Id
    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;
    
    @Id
    @Column(name = "name", length = 40)
    private String name;
    
    @Column(name = "description", length = 300, nullable = false)
    private String description;
    
    @Column(name = "collection", length = 16)
    private String collection;
    
    @Column(name = "book", length = 40)
    private String book;
    
    public Character() {}
    
    public Character(User user, String name, String description, String collection, String book) {
        this.user = user;
        this.name = name;
        this.description = description;
        this.collection = collection;
        this.book = book;
    }
    
    // Getters and Setters
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public String getCollection() { return collection; }
    public void setCollection(String collection) { this.collection = collection; }
    
    public String getBook() { return book; }
    public void setBook(String book) { this.book = book; }
}
