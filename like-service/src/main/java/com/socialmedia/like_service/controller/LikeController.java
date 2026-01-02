package com.socialmedia.like_service.controller;

import com.socialmedia.like_service.service.LikeService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/likes")
public class LikeController {

    private final LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }


    @PostMapping("/posts/{postId}")
    public void likePost(
            @RequestHeader("X-User-Id") Long userId,
            @PathVariable Long postId) {

        likeService.likePost(userId, postId);
    }




    @DeleteMapping("/posts/{postId}")
    public void unlikePost(
            @RequestHeader("X-User-Id") Long userId,
            @PathVariable Long postId) {

        likeService.unlikePost(userId, postId);
    }



    @GetMapping("/posts/{postId}/status")
    public boolean isPostLiked(
            @RequestHeader("X-User-Id") Long userId,
            @PathVariable Long postId) {

        return likeService.isPostLikedByUser(userId, postId);
    }


    @GetMapping("/posts/{postId}/count")
    public long getLikeCount(@PathVariable Long postId) {
        return likeService.getLikeCount(postId);
    }
}
