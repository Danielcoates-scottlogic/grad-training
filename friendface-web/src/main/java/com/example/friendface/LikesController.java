package com.example.friendface;


import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("likes")
public class LikesController {
    private final LikesService likesService;
    private final PostService postService;
    public LikesController(LikesService likesService, PostService postService) {
        this.likesService = likesService;
        this.postService = postService;
    }
    @PutMapping
    public ResponseEntity<?> updateLike(@Valid @RequestBody LikeDto info) {
        return new ResponseEntity<>(likesService.updateLike(info), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Boolean> checkLike(@Valid @RequestBody LikeDto info){
        return new ResponseEntity<>(likesService.hasUserLiked(info), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Integer> countLikes(@RequestParam Long postId) {
        return new ResponseEntity<>(postService.countLikes(postId), HttpStatus.OK);
    }

}
