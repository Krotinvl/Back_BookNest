package com.booknest.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class CreateBookDto {
    
    @NotBlank(message = "Название книги обязательно")
    private String title;
    
    @NotBlank(message = "Автор книги обязателен")  
    private String author;
    
    @NotBlank(message = "Жанр книги обязателен")
    private String genre;
    
    @NotNull(message = "Количество страниц обязательно")
    @Positive(message = "Количество страниц должно быть положительным")
    private Integer pages;
    
    private String description;
    private String publishing;
    private String dateOfPublication;
    private String cycle;
    private String image;        
    private String imageType;    
    
    public CreateBookDto() {}
    
    public CreateBookDto(String title, String author, String genre, Integer pages, 
    String description, String publishing, String dateOfPublication, 
     String cycle, String image, String imageType) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.pages = pages;
        this.description = description;
        this.publishing = publishing;
        this.dateOfPublication = dateOfPublication;
        this.cycle = cycle;
        this.image=image;
        this.imageType = imageType;
    }
    
    
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }
    
    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }
    
    public Integer getPages() { return pages; }
    public void setPages(Integer pages) { this.pages = pages; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public String getPublishing() { return publishing; }
    public void setPublishing(String publishing) { this.publishing = publishing; }
    
    public String getDateOfPublication() { return dateOfPublication; }
    public void setDateOfPublication(String dateOfPublication) { this.dateOfPublication = dateOfPublication; }
    
    public String getCycle() { return cycle; }
    public void setCycle(String cycle) { this.cycle = cycle; }
    
    
    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }
    
    public String getImage_type() { return imageType; }
    public void setImage_type(String imageType) { this.imageType = imageType; }
}