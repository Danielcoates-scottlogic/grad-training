package com.example.friendface;

import jakarta.validation.constraints.NotBlank;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping
    public List<User> getUsers() {
        return this.userService.getUsers();
    }

    @PostMapping ResponseEntity<User> addUser(@RequestParam @NotBlank String username) {
        return this.userService.addUser(username);
    }
}
