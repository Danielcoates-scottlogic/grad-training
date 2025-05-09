package com.example.friendface;


import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("users")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService, HandleValidationExceptions handleValidationExceptions) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<ReturnUsersDto> getUsers() {
        List<User> users = this.userService.getUsers();
        List<UserPost>dtoUsers  = new ArrayList<>();
        ReturnUsersDto dto = new ReturnUsersDto();
        for (User user: users) {
            UserPost conversion = new UserPost();
            conversion.setColour(user.getColour());
            conversion.setUsername(user.getUsername());
            dtoUsers.add(conversion);
        }
        dto.setUsers(dtoUsers);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<User> addUser(@Valid @RequestBody CreateUserDto dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        boolean userExists = userService.doesUserExist(user.getUsername());
        if (userExists) {
            return new ResponseEntity<>(new User(), HttpStatus.CONFLICT);
        }
        user.setColour(dto.getColour());
        user.setPassword(dto.getPassword());
        User response = this.userService.addUser(user);
        if (response.getUsername() == null || response.getUsername().isEmpty()) {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}