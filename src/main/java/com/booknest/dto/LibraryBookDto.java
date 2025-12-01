package com.booknest.dto;

import java.util.Base64;


public class LibraryBookDto {
    private Long bookId;
    private String bookTitle;
    private String bookAuthor;
    private String bookGenre;
    private String bookDescription;
    private String bookPublishing;
    private String bookDateOfPublication;
    private String bookCycle;
    private byte[] bookImage; 
    private String bookImage_type;
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

    public String getBookDescription() { return bookDescription; }
    public void setBookDescription(String bookDescription) { this.bookDescription = bookDescription; }

    public String getBookPublishing() { return bookPublishing; }
    public void setBookPublishing(String bookPublishing) { this.bookPublishing = bookPublishing; }

    public String getBookDateOfPublication() { return bookDateOfPublication; }
    public void setBookDateOfPublication(String bookDateOfPublication) { this.bookDateOfPublication = bookDateOfPublication; }
    
    public String getBookCycle() { return bookCycle; }
    public void setBookCycle(String bookCycle) { this.bookCycle = bookCycle; }

    public byte[] getBookImage() { return bookImage; }
    public void setBookImage(byte[] bookImage) { this.bookImage = bookImage; }
    
    public String getBookImage_type() { return bookImage_type; }
    public void setBookImage_type(String bookImage_type) { this.bookImage_type = bookImage_type; }
    
    public Integer getTotalPages() { return totalPages; }
    public void setTotalPages(Integer totalPages) { this.totalPages = totalPages; }
    
    public String getCollection() { return collection; }
    public void setCollection(String collection) { this.collection = collection; }
    
    public Integer getPagesRead() { return pagesRead; }
    public void setPagesRead(Integer pagesRead) { this.pagesRead = pagesRead; }
    
    public Integer getReadingProgress() { return readingProgress; }
    public void setReadingProgress(Integer readingProgress) { this.readingProgress = readingProgress; }
}