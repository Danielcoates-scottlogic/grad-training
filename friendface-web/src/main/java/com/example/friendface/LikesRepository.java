package com.example.friendface;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LikesRepository  extends JpaRepository<Like, String>{
    Like findByUsernameAndPostId(String username, int postId);
}


