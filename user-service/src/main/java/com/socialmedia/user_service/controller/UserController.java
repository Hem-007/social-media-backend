package com.socialmedia.user_service.controller;


import com.socialmedia.user_service.dto.UserProfileResponse;
import com.socialmedia.user_service.dto.UserProfileUpdateRequest;
import com.socialmedia.user_service.entity.UserProfile;
import com.socialmedia.user_service.service.UserProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserProfileService userProfileService;

    @PostMapping("/profile")
    public UserProfileResponse createUserProfile(
            @RequestHeader("X-User-Id") Long userId,
            @RequestHeader("X-Username") String username,
            @RequestHeader("X-Email") String email
    ) {
        UserProfile profile = userProfileService.createProfileIfNotExists(userId ,username, email);
        return mapToResponse(profile);
    }

    @GetMapping("/me")
    public UserProfileResponse getMyProfile(
            @RequestHeader("X-User-Id") Long userId
    ) {
        return mapToResponse(userProfileService.getProfileByUserId(userId));
    }

    @PutMapping("/me")
    public UserProfileResponse updateProfile(
            @RequestHeader("X-User-Id") Long userId,
            @Valid @RequestBody UserProfileUpdateRequest request
    ) {
        UserProfile updated = userProfileService.updateProfile(
                userId,
                request.getName(),
                request.getBio(),
                request.getProfileImage(),
                request.getCoverImage()

        );
        return mapToResponse(updated);
    }

    @GetMapping("/{username}")
    public UserProfileResponse getPublicProfile(
            @PathVariable String username
    ) {
        return mapToResponse(userProfileService.getProfileByUsername(username));
    }

    private UserProfileResponse mapToResponse(UserProfile profile) {
        return UserProfileResponse.builder()
                .userId(profile.getUserId())
                .username(profile.getUsername())
                .email(profile.getEmail())
                .name(profile.getName())
                .bio(profile.getBio())
                .profileImage(profile.getProfileImage())
                .coverImage(profile.getCoverImage())
                .followersCount(profile.getFollowersCount())
                .followingCount(profile.getFollowingCount())
                .build();
    }
}

