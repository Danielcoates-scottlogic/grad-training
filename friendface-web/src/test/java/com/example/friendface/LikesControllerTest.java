package com.example.friendface;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(LikesController.class)
public class LikesControllerTest {
    @MockBean
    LikesService likesService;
    @MockBean
    PostService postService;


    @Autowired
    MockMvc mockMvc;
    @MockBean
    private JwtUtil jwtUtil;
    @MockBean
    private LoginService loginService;
    @MockBean
    private UserService userService;


    @Test
    @WithMockUser
    void countLikes() throws Exception {
        Long postId = 22L;
        int likes = 22;

        when(postService.countLikes(postId)).thenReturn(likes);

        ResultActions result = mockMvc.perform(get("/likes")
                .param("postId", String.valueOf(postId)))
                .andExpect(status().isOk())
                .andExpect(content().string(String.valueOf(likes)));
        verify(postService).countLikes(postId);
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void checkLike_userHasLiked() throws Exception {
        LikeDto dto = new LikeDto();
        dto.setPostId(22L);
        dto.setUsername("Dan");
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(dto);

        when(likesService.hasUserLiked(refEq(dto))).thenReturn(true);

        ResultActions result = mockMvc.perform(post("/likes")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
        verify(likesService).hasUserLiked(refEq(dto));
    }

    @Test
    @WithMockUser
    void updateLike_existingLike() throws Exception {
        LikeDto dto = new LikeDto();
        dto.setUsername("Dan");
        dto.setPostId(22L);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(dto);

        when(likesService.updateLike(dto)).thenReturn(false);

        ResultActions result = mockMvc.perform(put("/likes")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(content().string("false"));
        verify(likesService).updateLike(refEq(dto));
    }
}
