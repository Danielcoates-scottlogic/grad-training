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

    public User addUser(User user) {
        if (user.getUsername().isEmpty()) {
            return null;
        }
        return userRepository.save(user);
    }
}
