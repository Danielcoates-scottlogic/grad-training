package com.example.friendface;


import org.springframework.stereotype.Service;

@Service
public class LikesService {
    private final LikesRepository likesRepository;

    public LikesService(LikesRepository likesRepository, UserRepository userRepository, PostRepository postRepository) {
        this.likesRepository = likesRepository;
    }


    public boolean checkIfLiked(String username, int postId) {
        Like result = likesRepository.findByUsernameAndPostId(username, postId);
        return result != null;
    }

    public boolean updateLike(LikeDto info) {
        Like existingLike = likesRepository.findByUsernameAndPostId(info.getUsername(), info.getPostId());
        if (existingLike != null) {
            likesRepository.delete(existingLike);
            return false;
        } else {
            Like newLike = new Like();
            newLike.setUsername(info.getUsername());
            newLike.setPostId(info.getPostId());
            likesRepository.save(newLike);
            return true;
        }
    }
}
