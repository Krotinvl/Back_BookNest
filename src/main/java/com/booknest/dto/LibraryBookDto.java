package com.booknest.dto;

public class LibraryBookDto {
    private BookDto book;
    private String collection;
    private Integer pagesRead;
    private Integer readingProgress; 
    
    public LibraryBookDto() {}
    
    public LibraryBookDto(BookDto book, String collection, Integer pagesRead, Integer readingProgress) {
        this.book = book;
        this.collection = collection;
        this.pagesRead = pagesRead;
        this.readingProgress = readingProgress;
    }
    
    // Getters and Setters
    public BookDto getBook() { return book; }
    public void setBook(BookDto book) { this.book = book; }
    
    public String getCollection() { return collection; }
    public void setCollection(String collection) { this.collection = collection; }
    
    public Integer getPagesRead() { return pagesRead; }
    public void setPagesRead(Integer pagesRead) { this.pagesRead = pagesRead; }
    
    public Integer getReadingProgress() { return readingProgress; }
    public void setReadingProgress(Integer readingProgress) { this.readingProgress = readingProgress; }
}