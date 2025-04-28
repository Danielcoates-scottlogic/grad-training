package com.example.friendface;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("users")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping
    public ResponseEntity<List<String>> getUsers() {
        List<User> users = this.userService.getUsers();
        ReturnUsersDto dto = new ReturnUsersDto();
        List<String> usernames = new ArrayList<>();
        for (User user: users) {
            usernames.add(user.getUsername());
        }
        dto.setUsernames(usernames);
        return new ResponseEntity<>(dto.getUsernames(),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody CreateUserDto dto) {
        if (dto.getUsername() == null || dto.getUsername().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        User user = new User();
        user.setUsername(dto.getUsername());
        User response = this.userService.addUser(user);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
