package com.example.friendface;

import jakarta.validation.constraints.NotBlank;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = this.userService.getUsers();
        UserDto dto = new UserDto();
        dto.setUsers(users);
        return new ResponseEntity<>(dto.getUsers(),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody @NotBlank User user) {
        UserDto dto = new UserDto();
        dto.setUsername(user.getUsername());
        return new ResponseEntity<>(this.userService.addUser(dto), HttpStatus.CREATED);
    }
}
