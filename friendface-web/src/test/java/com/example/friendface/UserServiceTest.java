package com.example.friendface;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;


import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @Test
    void addUser_shouldSaveEncodedUser_whenUsernameIsNotEmpty() {
        User inputUser = new User();
        inputUser.setUsername("Dan");
        inputUser.setPassword("Password");
        String encodedPassword = "encodedPassword";

        when(passwordEncoder.encode("Password")).thenReturn(encodedPassword);
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

        User savedUser = userService.addUser(inputUser);

        assertEquals("Dan", savedUser.getUsername());
        assertEquals(encodedPassword, savedUser.getPassword());
        verify(userRepository).save(any(User.class));
    }

    @Test
    void addUser_shouldReturnEmptyUser_whenUsernameIsEmpty() {
        User inputUser = new User();
        inputUser.setUsername("");
        inputUser.setPassword("Password");

        User result = userService.addUser(inputUser);

        assertNotNull(result);
        assertNull(result.getUsername());
        verifyNoInteractions(passwordEncoder, userRepository);
    }

    @Test
        String username = "Dan";
        when(userRepository.checkUserExists(username)).thenReturn(true);

        boolean exists = userService.doesUserExist(username);

        assertTrue(exists);
        verify(userRepository).checkUserExists(username);
    }

}
