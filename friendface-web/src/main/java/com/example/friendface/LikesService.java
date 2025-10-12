package com.example.friendface;


import org.springframework.stereotype.Service;

@Service
public class LikesService {
    private final LikesRepository likesRepository;

    public LikesService(LikesRepository likesRepository) {
        this.likesRepository = likesRepository;
    }


    public boolean updateLike(LikeDto info) {
        Likes existingLike = likesRepository.findByUsernameAndPostId(info.getUsername(), info.getPostId());
        if (existingLike != null) {
            likesRepository.delete(existingLike);
            return false;
        } else {
            Likes newLike = new Likes();
            newLike.setUsername(info.getUsername());
            newLike.setPostId(info.getPostId());
            likesRepository.save(newLike);
            return true;
        }
    }



    public boolean hasUserLiked(LikeDto info) {
        Likes existingLike = likesRepository.findByUsernameAndPostId(info.getUsername(), info.getPostId());
        return !(existingLike == null);
    }
}
