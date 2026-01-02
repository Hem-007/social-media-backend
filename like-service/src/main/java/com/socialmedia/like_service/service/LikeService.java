package com.socialmedia.like_service.service;

public interface LikeService {

    void likePost(Long userId, Long postId);

    void unlikePost(Long userId, Long postId);

    boolean isPostLikedByUser(Long userId, Long postId);

    long getLikeCount(Long postId);
}
