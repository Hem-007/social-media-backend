package com.socialmedia.follow_service.service;

import com.socialmedia.follow_service.exception.SelfFollowNotAllowedException;
import com.socialmedia.follow_service.model.Follow;
import com.socialmedia.follow_service.repository.FollowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FollowServiceImpl implements FollowService{


    private final FollowRepository followRepository;

    public FollowServiceImpl(FollowRepository followRepository) {
        this.followRepository = followRepository;
    }

    @Override
    public void follow(Long followerId, Long followeeId) {

        if(followerId.equals(followeeId)){
            throw new SelfFollowNotAllowedException("Users cannot follow themselves");
        }

        boolean alreadyFollowing = followRepository.findByFollowerIdAndFolloweeId(followerId,followeeId).isPresent();

        if(alreadyFollowing){
            return;
        }

        Follow follow = new Follow();
        follow.setFollowerId(followerId);
        follow.setFolloweeId(followeeId);

        followRepository.save(follow);
    }

    @Override
    public void unfollow(Long followerId, Long followeeId) {

        Optional<Follow> follow = followRepository.findByFollowerIdAndFolloweeId(followerId,followeeId);

        follow.ifPresent(followRepository::delete);

    }

    @Override
    @Transactional(readOnly = true)
    public boolean isFollowing(Long followerId, Long followeeId) {
        return followRepository.findByFollowerIdAndFolloweeId(followerId, followeeId).isPresent();
    }

    @Override
    @Transactional(readOnly = true)
    public long getFollowerCount(Long userId) {
        return followRepository.countByFolloweeId(userId);
    }

    @Override
    @Transactional(readOnly = true)
    public long getFollowingCount(Long userId) {
        return followRepository.countByFollowerId(userId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Long> getFollowingUserIds(Long followerId) {
        return followRepository.findByFollowerId(followerId)
                .stream()
                .map(Follow::getFolloweeId)
                .toList();
    }
}
