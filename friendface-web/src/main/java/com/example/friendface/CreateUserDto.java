package com.example.friendface;

import jakarta.validation.constraints.NotEmpty;


public class CreateUserDto {

    @NotEmpty(message = "Username Required")
    private String username;
    private String colour;
    @NotEmpty(message = "Password required")
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
