package com.example.friendface;

import jakarta.validation.constraints.NotBlank;

import java.util.List;

public class UserDto {
    @NotBlank
    private String username;
    public List<User> users;

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<User> getUsers() {
        return users;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
