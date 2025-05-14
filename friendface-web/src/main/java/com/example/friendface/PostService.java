package com.example.friendface;


import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public List<Post> getPosts(){
        return postRepository.findAll();
    }

    public Post addPost(ReturnPostDto dto) {
        if (dto.getAuthor() == null || dto.getContent() == null || dto.getAuthor().isEmpty() || dto.getContent().isEmpty()) {
            return new Post();
        }
        Post post = new Post();
        if (dto.getPostImage() != null && !dto.getPostImage().isEmpty()) {
            String cleanedString = dto.getPostImage().split(",")[1];
            byte[] img = Base64.getDecoder().decode(cleanedString);
            post.setImage(img);
        }
        post.setContents(dto.getContent());
        User author = userRepository.findById(dto.getAuthor())
                .orElseThrow(() -> new RuntimeException("User not found"));
        post.setUser(author);
        return postRepository.save(post);
    }
    public int countLikes(Long postId) {
        return postRepository.findLikesById(postId);
    }
}
