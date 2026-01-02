package com.socialmedia.follow_service.service;

import java.util.List;

public interface FollowService {
    void follow(Long followerId, Long followeeId);

    void unfollow(Long followerId, Long followeeId);

    boolean isFollowing(Long followerId, Long followeeId);

    long getFollowerCount(Long userId);

    long getFollowingCount(Long userId);

    List<Long> getFollowingUserIds(Long followerId);

}
