package com.example.friendface;

import org.springframework.stereotype.Service;


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

    public CreateUserDto addUser(User user) {
        if (user.getUsername().isEmpty()){
            return new CreateUserDto();
        }
        CreateUserDto dto = new CreateUserDto();
        dto.setUsername(user.getUsername());
        return userRepository.save(dto);
    }
}
