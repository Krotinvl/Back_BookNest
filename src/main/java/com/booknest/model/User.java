package com.booknest.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    private String username;
    
    private String name;
    private String email;
    private String password;
    private String description;
    private String telephone;
    private Integer activeDays;
    
    // убрали cascade... пока... наверное
    @OneToMany(mappedBy = "user")
    private List<Library> libraries = new ArrayList<>();
    
    public User() {}
    
    // Getters and Setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getTelephone() { return telephone; }
    public void setTelephone(String telephone) { this.telephone = telephone; }
    public Integer getActiveDays() { return activeDays; }
    public void setActiveDays(Integer activeDays) { this.activeDays = activeDays; }
    public List<Library> getLibraries() { return libraries; }
    public void setLibraries(List<Library> libraries) { this.libraries = libraries; }
}