package com.example.friendface;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
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
    public ResponseEntity<Post> addPost(@RequestBody ReturnPostDto post) {
        Post response = this.postService.addPost(post);
        if (response.getUser().getUsername().isEmpty() || response.getUser().getUsername() == null){
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        response.getUser().setPassword("");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
