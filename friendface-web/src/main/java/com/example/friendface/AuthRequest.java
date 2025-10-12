package com.example.friendface;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthRequest {
    @NotBlank(message = "Username cannot be blank")
    @Size(min = 3, max = 99)
    private String username;

    @NotBlank(message = "Password cannot be blank")
    @Size(min = 3, max = 99)
    private String password;
}
