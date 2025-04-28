package com.example.friendface;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import java.util.List;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {
    @MockBean
    UserService userService;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void getUsers() throws Exception {
        User user1 = new User();
        User user2 = new User();
        user1.setUsername("Alice");
        user2.setUsername("Bob");
        when(userService.getUsers()).thenReturn(List.of(user1, user2));

        ResultActions result = mockMvc.perform(get("/users"))
                .andExpect(status().isOk());
        verify(userService).getUsers();
    }

    @Test
    public void addUser() throws Exception {
        CreateUserDto user = new CreateUserDto();
        user.setUsername("Dan");
        User returnUser = new User();
        returnUser.setUsername("Dan");
        when(userService.addUser(any(User.class))).thenReturn(returnUser);

        ResultActions result = mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\":\"Dan\"}"))
                .andExpect(status().isCreated());
        verify(userService).addUser(any(User.class));
    }

    @Test
    public void badUser() throws Exception {
        CreateUserDto dto = new CreateUserDto();
        dto.setUsername("");
        when(userService.addUser(any(User.class))).thenReturn(null);

        ResultActions result = mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"\"}"))
                .andExpect(status().isBadRequest());
        verify(userService, never()).addUser(any(User.class));
    }


}
