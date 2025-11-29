package com.booknest.model;

import java.io.Serializable;
import java.util.Objects;

public class ReviewId implements Serializable {
    private Long book;
    private String user;
    
    public ReviewId() {}
    
    public ReviewId(Long book, String user) {
        this.book = book;
        this.user = user;
    }
    
    public Long getBook() { return book; }
    public void setBook(Long book) { this.book = book; }
    
    public String getUser() { return user; }
    public void setUser(String user) { this.user = user; }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ReviewId)) return false;
        ReviewId that = (ReviewId) o;
        return Objects.equals(book, that.book) && Objects.equals(user, that.user);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(book, user);
    }
}