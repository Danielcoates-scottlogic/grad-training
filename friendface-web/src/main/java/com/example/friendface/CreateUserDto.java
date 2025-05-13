package com.example.friendface;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Blob;


@Builder
@Setter
@Getter
public class CreateUserDto {

    @NotBlank(message = "Username Required")
    @Size(min = 3, max = 99)
    private String username;
    @Builder.Default
    private String colour = "#000000";
    @NotBlank(message = "Password required")
    @Size(min = 3, max = 99)
    private String password;
    private String profileImg;
}
