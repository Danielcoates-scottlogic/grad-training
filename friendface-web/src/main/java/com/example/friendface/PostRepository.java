package com.example.friendface;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostRepository extends  JpaRepository<Post, String> {
    @Query("SELECT likes FROM Post WHERE id = :id")
    Integer findLikesById(@Param("id") Long id);
}


