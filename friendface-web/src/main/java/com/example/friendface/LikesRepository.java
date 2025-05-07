package com.example.friendface;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikesRepository  extends JpaRepository<Like, String>{
    Like findByUsernameAndPostId(String username, int postId);

    List<Like> findByPostId(int postId);
}


