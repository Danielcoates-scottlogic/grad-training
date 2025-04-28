package com.example.friendface;

import jakarta.validation.constraints.NotBlank;


public class CreateUserDto {
    @NotBlank
    private String username;



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
