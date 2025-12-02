package com.booknest.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class PagesPerDayId implements Serializable {
    private String user;
    private LocalDate dateDay;

    public PagesPerDayId() {}

    public PagesPerDayId(String user, LocalDate dateDay) {
        this.user = user;
        this.dateDay = dateDay;
    }

    public String getUser() { return user; }
    public void setUser(String user) { this.user = user; }
    
    public LocalDate getDateDay() { return dateDay; }
    public void setDateDay(LocalDate dateDay) { this.dateDay = dateDay; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PagesPerDayId)) return false;
        PagesPerDayId that = (PagesPerDayId) o;
        return Objects.equals(user, that.user) && Objects.equals(dateDay, that.dateDay);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, dateDay);
    }
}
