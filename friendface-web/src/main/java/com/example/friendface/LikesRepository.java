package com.example.friendface;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikesRepository  extends JpaRepository<Likes, String>{
    Likes findByUsernameAndPostId(String username, Long postId);

    List<Likes> findByPostId(Long postId);
}


