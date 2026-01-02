package com.socialmedia.follow_service.repository;

import com.socialmedia.follow_service.model.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FollowRepository extends JpaRepository<Follow , Long> {

    Optional<Follow> findByFollowerIdAndFolloweeId(Long followerId, Long followeeId);

    Long countByFollowerId(Long followerId);

    Long countByFolloweeId(Long followeeId);

    List<Follow> findByFollowerId(Long followerId);

}
