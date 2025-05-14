package com.example.friendface;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LoginServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private LoginService loginService;

    @Test
    void findByUsername_shouldFindUser() {
        String username = "Dan";
        User user = new User();
        user.setUsername("Dan");
        when(userRepository.findById(username)).thenReturn(Optional.of(user));
        User result = loginService.findByUsername(username);

        assertEquals("Dan", result.getUsername());
    }

    @Test
    void findByUsername_shouldntFindUser() {
        String username = "I'm not real";
        User user = new User();
        user.setUsername("Dan");
        when(userRepository.findById(username)).thenReturn(Optional.empty());
        User result = loginService.findByUsername(username);

        assertEquals(null, result.getUsername());
    }

}
