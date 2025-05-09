package com.example.friendface;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface UserRepository extends JpaRepository<User, String> {
    @Query("select count(p) = 1 from User p where username = ?1")
    boolean checkUserExists(String username);
}
