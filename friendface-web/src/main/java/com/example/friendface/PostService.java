package com.example.friendface;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> getPosts(){
        return postRepository.findAll();
    }
    public Post addPost(Post post){
        return postRepository.save(post);
    }
}
