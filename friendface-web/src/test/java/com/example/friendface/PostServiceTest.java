package com.example.friendface;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PostServiceTest {

    @Mock
    private PostRepository postRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private PostService postService;

    @Test
    void countLikes_shouldReturnNumberOfLikes() {
        Long id = 22L;

        when(postRepository.findLikesById(id)).thenReturn(5);

        int result = postService.countLikes(id);

        assertEquals(5, result);
    }

    @Test
    void addPost_addValidPost() {
        ReturnPostDto dto = new ReturnPostDto();
        dto.setAuthor("dan");
        dto.setContent("My post");

        User returnUser = new User();
        returnUser.setUsername("dan");

        when(userRepository.findById("dan")).thenReturn(Optional.of(returnUser));
        when(postRepository.save(ArgumentMatchers.<Post>any()))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Post result = postService.addPost(dto);

        assertNotNull(result);
        assertEquals("My post", result.getContents());
        assertEquals(returnUser, result.getUser());
    }

    @Test
    void addPost_addInvalidPost_noContent() {
        ReturnPostDto dto = new ReturnPostDto();
        dto.setAuthor("dan");
        dto.setContent("");

        User returnUser = new User();
        returnUser.setUsername("dan");


        Post result = postService.addPost(dto);

        assertNull(result.getContents());
        assertNull(result.getUser());
    }

    @Test
    void addPost_addInvalidPost_noUser() {
        ReturnPostDto dto = new ReturnPostDto();
        dto.setAuthor("");
        dto.setContent("content");

        User returnUser = new User();
        returnUser.setUsername("");


        Post result = postService.addPost(dto);

        assertNull(result.getContents());
        assertNull(result.getUser());
    }

}
