package com.socialmedia.user_service.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserProfileResponse {

    private Long userId;
    private String username;
    private String email;
    private String name;
    private String bio;
    private String profileImage;
    private String coverImage;
    private Integer followersCount;
    private Integer followingCount;
}
