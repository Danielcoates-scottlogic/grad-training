package com.example.friendface;

import jakarta.validation.constraints.NotEmpty;

public class ReturnPostDto {
    @NotEmpty(message = "Content can't be empty")
    private String content;
    @NotEmpty(message = "Author can't be empty")
    private String author;

    public String getContent() {
        return content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
