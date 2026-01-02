package com.socialmedia.user_service.service;


import com.socialmedia.user_service.entity.UserProfile;
import com.socialmedia.user_service.exception.UserNotFoundException;
import com.socialmedia.user_service.repository.UserProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserProfileService implements UserProfileServiceInterface {

    private final UserProfileRepository userProfileRepository;

    @Override
    public UserProfile createProfileIfNotExists(Long userId , String username, String email) {
        return userProfileRepository.findByUserId(userId)
                .orElseGet(() -> {
                    UserProfile profile = UserProfile.builder()
                            .userId(userId)
                            .username(username)
                            .email(email)
                            .build();
                    return userProfileRepository.save(profile);
                });
    }

    @Override
    public UserProfile getProfileByUserId(Long userId) {

        return userProfileRepository.findByUserId(userId).orElseThrow(
                () -> new UserNotFoundException("User profile not found"));

    }

    @Override
    public UserProfile getProfileByUsername(String username) {
        return userProfileRepository.findByUsername(username)
                .orElseThrow(() ->
                        new UserNotFoundException("User profile not found"));
    }

    @Override
    public UserProfile updateProfile(
            Long userId,
            String name,
            String bio,
            String profileImage,
            String coverImage) {

        UserProfile profile = getProfileByUserId(userId);

        profile.setName(name);
        profile.setBio(bio);
        profile.setProfileImage(profileImage);
        profile.setCoverImage(coverImage);

        return userProfileRepository.save(profile);
    }
}
