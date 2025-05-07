package com.example.friendface;


import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("likes")
public class LikesController {
    private final LikesService likesService;
    public LikesController(LikesService likesService, HandleValidationExceptions handleValidationExceptions) {
        this.likesService = likesService;
    }
    @PostMapping
    public boolean updateLike(@Valid @RequestBody LikeDto info) {
        return likesService.updateLike(info);
    }

    @GetMapping
    public int countLikes(@RequestParam int postId) {
        return likesService.countLikes(postId);
    }

}
