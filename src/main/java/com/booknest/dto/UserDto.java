package com.booknest.dto;

public class UserDto {
    private String username;
    private String name;
    private String email;
    private String description;
    private String telephone;
    private Integer activeDays;
    
    public UserDto() {}
    

    public UserDto(String username, String name, String email, 
                   String description, String telephone, Integer activeDays) {
        this.username = username;
        this.name = name;
        this.email = email;
        this.description = description;
        this.telephone = telephone;
        this.activeDays = activeDays;
    }

     // Getters and Setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public String getTelephone() { return telephone; }
    public void setTelephone(String telephone) { this.telephone = telephone; }
    
    public Integer getActiveDays() { return activeDays; }
    public void setActiveDays(Integer activeDays) { this.activeDays = activeDays; }
}
