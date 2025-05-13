package com.example.friendface;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LikeDto {
    @NotBlank(message = "Username can't be blank")
    @Size(min = 3, max = 99)
    private String username;
    @NotNull(message = "Post ID can't be null")
    @Positive
    private Long postId;
}
