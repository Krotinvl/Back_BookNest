package com.booknest.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "all_books")
public class Book {
    @Id
    private Long id;
    
    private String title;
    private String author;
    private String genre;
    private Integer pages;
    
    @Column(length = 500)
    private String description;
    
    private String publishing;
    private String dateOfPublication;
    private String cycle;
    @Lob
    private byte[] image; 
    
    private String image_type;
    
    @OneToMany(mappedBy = "book")
    private List<Library> libraries = new ArrayList<>();
    
    public Book() {}
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
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
    public byte[] getImage() { return image;}
    public void setImage(byte[] image) { this.image = image;}
    public String getImage_type() { return image_type;}
    public void setImage_type(String image_type) { this.image_type = image_type;}
    public List<Library> getLibraries() { return libraries; }
    public void setLibraries(List<Library> libraries) { this.libraries = libraries; }
}