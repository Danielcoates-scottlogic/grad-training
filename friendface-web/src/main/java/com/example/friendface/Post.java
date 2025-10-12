package com.example.friendface;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name="posts")
@Getter @Setter
public class Post {
    @Id
    @Positive
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotBlank(message = "Post content cant be blank")
    private String contents;

    @Column
    private int likes = 0;

    @Column
    private Date date = new Date();

    @ManyToOne
    @JoinColumn(name = "username", referencedColumnName = "username", nullable = false)
    private User user;

    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private byte[] image;

}
