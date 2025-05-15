package com.example.friendface;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import java.util.List;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PostController.class)
public class PostControllerTest {
    @MockBean
    PostService postService;

    @Autowired
    MockMvc mockMvc;
    @MockBean
    private JwtUtil jwtUtil;
    @MockBean
    private LoginService loginService;

    @Test
    @WithMockUser(roles = "ADMIN")
    public void getPosts() throws Exception {
        Post post1 = new Post();
        Post post2 = new Post();
        when(postService.getPosts()).thenReturn(List.of(post1, post2));

        ResultActions response = mockMvc.perform(get("/posts"))
                .andExpect(status().isOk());
        verify(postService).getPosts();
    }


    @Test
    @WithMockUser
    public void addPost() throws Exception {
        Post post = new Post();
        post.setId(4L);
        User user = new User();
        user.setPassword("1234");
        user.setUsername("Dan");
        post.setUser(user);
        when(postService.addPost(any(ReturnPostDto.class))).thenReturn(post);

        ResultActions result = mockMvc.perform(post("/posts")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"author\":\"Dan\", \"content\":\"post here\"}"))
                .andExpect(status().isCreated());
        verify(postService).addPost(any(ReturnPostDto.class));
    }

    @Test
    @WithMockUser
    public void badPost() throws Exception {
        Post post = new Post();
        post.setId(4L);
        User user = new User();
        user.setPassword("");
        user.setUsername("");
        post.setUser(user);
        when(postService.addPost(any(ReturnPostDto.class))).thenReturn(post);

        ResultActions result = mockMvc.perform(post("/posts")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"author\":\"Dan\", \"content\":\"post here\"}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser
    public void postReturn() throws Exception {
        Post post = new Post();
        post.setId(4L);
        User user = new User();
        user.setUsername("dan");
        user.setPassword("");
        post.setUser(user);

        when(postService.addPost(any(ReturnPostDto.class))).thenReturn(post);

        mockMvc.perform(post("/posts")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"author\":\"Dan\", \"content\":\"post here\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(4))
                .andExpect(jsonPath("$.user.username").value("dan"));
    }



}
