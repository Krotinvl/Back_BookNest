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

class LibraryId implements java.io.Serializable {
    private String user;
    private Long book;
    
    public LibraryId() {}
    
    public LibraryId(String user, Long book) {
        this.user = user;
        this.book = book;
    }
    
    public String getUser() { return user; }
    public void setUser(String user) { this.user = user; }
    
    public Long getBook() { return book; }
    public void setBook(Long book) { this.book = book; }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LibraryId)) return false;
        LibraryId that = (LibraryId) o;
        return user.equals(that.user) && book.equals(that.book);
    }
    
    @Override
    public int hashCode() {
        return user.hashCode() + book.hashCode();
    }
}