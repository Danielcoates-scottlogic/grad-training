package com.example.friendface;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


public class CreateUserDto {
    @NotBlank(message = "Username can't be blank")
    @Size(min=1, max=49)
    private String username;



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
