package com.socialmedia.follow_service.controller;

import com.socialmedia.follow_service.service.FollowService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/follows")
public class FollowController {

    private final FollowService followService;

    public FollowController(FollowService followService) {
        this.followService = followService;
    }


    @PostMapping("/{userId}")
    public void followUser(
            @RequestHeader("X-User-Id") Long followerId,
            @PathVariable Long userId) {

        followService.follow(followerId, userId);
    }


    @DeleteMapping("/{userId}")
    public void unfollowUser(
            @RequestHeader("X-User-Id") Long followerId,
            @PathVariable Long userId) {

        followService.unfollow(followerId, userId);
    }


    @GetMapping("/{userId}/status")
    public boolean isFollowing(
            @RequestHeader("X-User-Id") Long followerId,
            @PathVariable Long userId) {

        return followService.isFollowing(followerId, userId);
    }


    @GetMapping("/{userId}/followers/count")
    public long getFollowerCount(@PathVariable Long userId) {
        return followService.getFollowerCount(userId);
    }


    @GetMapping("/{userId}/following/count")
    public long getFollowingCount(@PathVariable Long userId) {
        return followService.getFollowingCount(userId);
    }

    @GetMapping("/following/{userId}")
    public List<Long> getFollowing(
            @PathVariable Long userId) {

        return followService.getFollowingUserIds(userId);
    }

}
