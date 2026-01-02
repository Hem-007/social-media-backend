package com.socialmedia.user_service.service;

import com.socialmedia.user_service.entity.UserProfile;

public interface UserProfileServiceInterface {

    UserProfile createProfileIfNotExists(Long userId, String username, String email);

    UserProfile getProfileByUserId(Long userId);

    UserProfile getProfileByUsername(String username);

    UserProfile updateProfile(
            Long userId,
            String name,
            String bio,
            String profileImage,
            String coverImage
    );


}
