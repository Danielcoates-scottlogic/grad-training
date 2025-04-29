package com.example.friendface;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("posts")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;

    }

    @GetMapping
    public ResponseEntity<List<Post>> getPosts() {
        List<Post> posts = this.postService.getPosts();
        return new ResponseEntity<>(posts,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Post> addPost(@RequestBody Post post) {
        Post response = this.postService.addPost(post);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
