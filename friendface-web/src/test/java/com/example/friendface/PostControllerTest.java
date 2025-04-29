package com.example.friendface;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.client.ResponseActions;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import java.util.List;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PostController.class)
public class PostControllerTest {
    @MockBean
    PostService postService;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void getPosts() throws Exception {
        Post post1 = new Post();
        Post post2 = new Post();
        when(postService.getPosts()).thenReturn(List.of(post1, post2));

        ResultActions response = mockMvc.perform(get("/posts"))
                .andExpect(status().isOk());
        verify(postService).getPosts();
    }


    @Test
    public void addPost() throws Exception {
        Post post = new Post();
        post.setId(4L);
        when(postService.addPost(any(Post.class))).thenReturn(post);

        ResultActions result = mockMvc.perform(post("/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"Dan\"}"))
                .andExpect(status().isCreated());
        verify(postService).addPost(any(Post.class));
    }

}
