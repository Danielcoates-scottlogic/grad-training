package com.example.friendface;


import org.springframework.stereotype.Service;

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
        if (dto.getAuthor().isEmpty() || dto.getContent().isEmpty() || dto.getAuthor() == null || dto.getContent() == null) {
            return new Post();
        }
        Post post = new Post();
        post.setContents(dto.getContent());
        User author = userRepository.findById(dto.getAuthor())
                .orElseThrow(() -> new RuntimeException("User not found"));
        post.setUser(author);
        return postRepository.save(post);
    }
}
