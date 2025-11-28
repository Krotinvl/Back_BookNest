package com.booknest.model;

import jakarta.persistence.*;

@Entity
@Table(name = "libraries")
@IdClass(LibraryId.class)
public class Library {
    @Id
    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;
    
    @Id
    @ManyToOne
    @JoinColumn(name = "id_book")
    private Book book;
    
    private String collection;
    private Integer pages;
    
    public Library() {}
    
    // Getters and Setters
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    
    public Book getBook() { return book; }
    public void setBook(Book book) { this.book = book; }
    
    public String getCollection() { return collection; }
    public void setCollection(String collection) { this.collection = collection; }
    
    public Integer getPages() { return pages; }
    public void setPages(Integer pages) { this.pages = pages; }
}



