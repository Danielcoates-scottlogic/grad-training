package com.example.friendface;

import jakarta.persistence.*;

@Entity
@Table(name="users")
public class User {
    @Id
    @Column
    private String username;
    @Column
    private String colour;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }
}