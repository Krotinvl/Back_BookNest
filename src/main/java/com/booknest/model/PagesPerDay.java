package com.booknest.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "pages_per_day")
@IdClass(PagesPerDayId.class)
public class PagesPerDay {
    @Id
    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    @Id
    private LocalDate dateDay;

    private Integer pages;

    public PagesPerDay() {}

    public PagesPerDay(User user, LocalDate dateDay, Integer pages) {
        this.user = user;
        this.dateDay = dateDay;
        this.pages = pages;
    }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    
    public LocalDate getDateDay() { return dateDay; }
    public void setDateDay(LocalDate dateDay) { this.dateDay = dateDay; }
    
    public Integer getPages() { return pages; }
    public void setPages(Integer pages) { this.pages = pages; }
}
