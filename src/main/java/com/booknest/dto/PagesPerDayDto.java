package com.booknest.dto;

import java.time.LocalDate;

public class PagesPerDayDto {
    private LocalDate dateDay;
    private Integer pages;

    public PagesPerDayDto() {}

    public PagesPerDayDto(LocalDate dateDay, Integer pages) {
        this.dateDay = dateDay;
        this.pages = pages;
    }

    public LocalDate getDateDay() { return dateDay; }
    public void setDateDay(LocalDate dateDay) { this.dateDay = dateDay; }
    
    public Integer getPages() { return pages; }
    public void setPages(Integer pages) { this.pages = pages; }
}
