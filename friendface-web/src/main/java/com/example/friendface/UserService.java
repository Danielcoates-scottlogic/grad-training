package com.example.friendface;

import jakarta.validation.constraints.NotBlank;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public ResponseEntity<User> addUser(UserDto dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        userRepository.save(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
