package com.booknest.dto;

import jakarta.validation.constraints.NotNull;

public class AddToLibraryDto {
    
    @NotNull(message = "ID книги обязательно")
    private Long bookId;

    public AddToLibraryDto() {}
    
    // Конструктор
    public AddToLibraryDto(Long bookId) {
        this.bookId = bookId;
    }
    
    // Getters and Setters
    public Long getBookId() { return bookId; }
    public void setBookId(Long bookId) { this.bookId = bookId; }
}