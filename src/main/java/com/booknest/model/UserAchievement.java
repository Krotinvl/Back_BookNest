package com.booknest.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "user_achievements")
@IdClass(UserAchievementId.class)
public class UserAchievement {
    @Id
    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;
    
    @Id
    @Column(name = "id_achievement")
    private Long achievementId;
    
    private LocalDate dateAchievement;
    
    public UserAchievement() {}
    
    // Getters and Setters
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    
    public Long getAchievementId() { return achievementId; }
    public void setAchievementId(Long achievementId) { this.achievementId = achievementId; }
    
    public LocalDate getDateAchievement() { return dateAchievement; }
    public void setDateAchievement(LocalDate dateAchievement) { this.dateAchievement = dateAchievement; }
}

class UserAchievementId implements java.io.Serializable {
    private String user;
    private Long achievementId;
    
    public UserAchievementId() {}
    
    public UserAchievementId(String user, Long achievementId) {
        this.user = user;
        this.achievementId = achievementId;
    }
    
    // Геттеры и сеттеры сука
    public String getUser() { return user; }
    public void setUser(String user) { this.user = user; }
    
    public Long getAchievementId() { return achievementId; }
    public void setAchievementId(Long achievementId) { this.achievementId = achievementId; }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserAchievementId)) return false;
        UserAchievementId that = (UserAchievementId) o;
        return user.equals(that.user) && achievementId.equals(that.achievementId);
    }
    
    @Override
    public int hashCode() {
        return user.hashCode() + achievementId.hashCode();
    }
}