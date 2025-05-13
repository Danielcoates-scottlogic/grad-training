package com.example.friendface;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ReturnPostDto {
    @NotEmpty(message = "Content can't be empty")
    private String content;
    @NotEmpty(message = "Author can't be empty")
    private String author;

}
