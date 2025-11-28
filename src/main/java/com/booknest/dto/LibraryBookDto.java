package com.booknest.dto;

public class LibraryBookDto {
    private Long bookId;
    private String bookTitle;
    private String bookAuthor;
    private String bookGenre;
    private Integer totalPages;
    private String collection;
    private Integer pagesRead;
    private Integer readingProgress; 
    
    public LibraryBookDto() {}
    
    // Getters and Setters
    public Long getBookId() { return bookId; }
    public void setBookId(Long bookId) { this.bookId = bookId; }
    
    public String getBookTitle() { return bookTitle; }
    public void setBookTitle(String bookTitle) { this.bookTitle = bookTitle; }
    
    public String getBookAuthor() { return bookAuthor; }
    public void setBookAuthor(String bookAuthor) { this.bookAuthor = bookAuthor; }
    
    public String getBookGenre() { return bookGenre; }
    public void setBookGenre(String bookGenre) { this.bookGenre = bookGenre; }
    
    public Integer getTotalPages() { return totalPages; }
    public void setTotalPages(Integer totalPages) { this.totalPages = totalPages; }
    
    public String getCollection() { return collection; }
    public void setCollection(String collection) { this.collection = collection; }
    
    public Integer getPagesRead() { return pagesRead; }
    public void setPagesRead(Integer pagesRead) { this.pagesRead = pagesRead; }
    
    public Integer getReadingProgress() { return readingProgress; }
    public void setReadingProgress(Integer readingProgress) { this.readingProgress = readingProgress; }
}