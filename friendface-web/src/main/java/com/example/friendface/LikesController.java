package com.example.friendface;


import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

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
    @PutMapping()
    public boolean updateLike(@Valid @RequestBody LikeDto info) {
        return likesService.updateLike(info);
    }

    @PostMapping()
    public boolean checkLike(@Valid @RequestBody LikeDto info){ return likesService.hasUserLiked(info);}

    @GetMapping
    public int countLikes(@RequestParam Long postId) {
        return postService.countLikes(postId);
    }

}
