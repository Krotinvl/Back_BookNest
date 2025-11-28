package com.booknest.model;

import java.io.Serializable;
import java.util.Objects;

public class LibraryId implements Serializable {
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
        return Objects.equals(user, that.user) && Objects.equals(book, that.book);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(user, book);
    }
}