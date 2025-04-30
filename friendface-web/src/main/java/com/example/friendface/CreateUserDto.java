package com.example.friendface;

import jakarta.validation.constraints.NotBlank;



public class CreateUserDto {

    @NotBlank
    private String username;
    private String colour;
    private String password;



    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
