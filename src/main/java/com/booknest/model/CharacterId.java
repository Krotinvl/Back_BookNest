package com.booknest.model;

import java.io.Serializable;
import java.util.Objects;

public class CharacterId implements Serializable {
    private String user;
    private String name;
    
    public CharacterId() {}
    
    public CharacterId(String user, String name) {
        this.user = user;
        this.name = name;
    }
    
    public String getUser() { return user; }
    public void setUser(String user) { this.user = user; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CharacterId)) return false;
        CharacterId that = (CharacterId) o;
        return user.equals(that.user) && name.equals(that.name);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(user, name);
    }
}
