package com.example.friendface;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LikesServiceTest {
    
    @Mock
    private LikesRepository likesRepository;
    
    @InjectMocks
    LikesService likesService;
    
    @Test
    void hasUserLiked_withValidUser() {
        LikeDto dto = new LikeDto();
        dto.setUsername("Dan");
        dto.setPostId(22L);

        Likes like = new Likes();
        like.setUsername("Dan");
        like.setPostId(22L);
        like.setId(1);
        
        when(likesRepository.findByUsernameAndPostId(dto.getUsername(), dto.getPostId())).thenReturn(like);
        boolean result = likesService.hasUserLiked(dto);

        assertEquals(true, result);
    }
    @Test
    void hasUserLiked_withInvalidUser() {
        LikeDto dto = new LikeDto();
        dto.setUsername("Not a user");
        dto.setPostId(22L);


        when(likesRepository.findByUsernameAndPostId(dto.getUsername(), dto.getPostId())).thenReturn(null);
        boolean result = likesService.hasUserLiked(dto);

        assertEquals(false, result);
    }
}
