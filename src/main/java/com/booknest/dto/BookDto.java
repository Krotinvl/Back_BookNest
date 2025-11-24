package com.booknest.dto;


public class BookDto {
    private Long id;
    private String title;
    private String author;
    private String genre;
    private Integer pages;
    private String description;
    private String publishing;
    private String dateOfPublication;
    private String cycle;
    private byte[] image; 
    private String image_type;
    
    public BookDto() {}
    
    // Конструктор ихихихиххихихих
    public BookDto(Long id, String title, String author, String genre, Integer pages, 
                   String description, String publishing, String dateOfPublication, String cycle, byte[] image, String image_type) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.pages = pages;
        this.description = description;
        this.publishing = publishing;
        this.dateOfPublication = dateOfPublication;
        this.cycle = cycle;
        this.image= image;
        this.image_type= image_type;
    }
    
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
}