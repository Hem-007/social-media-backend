package com.socialmedia.user_service.repository;

import com.socialmedia.user_service.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {

    Optional<UserProfile> findByUserId(Long userId);

    Optional<UserProfile> findByUsername(String username);

    boolean existsByUserId(Long userId);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}
