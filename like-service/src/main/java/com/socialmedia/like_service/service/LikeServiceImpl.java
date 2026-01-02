package com.socialmedia.like_service.service;

import com.socialmedia.like_service.entity.Like;
import com.socialmedia.like_service.repository.LikeRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LikeServiceImpl implements LikeService {

    private final LikeRepository likeRepository;

    public LikeServiceImpl(LikeRepository likeRepository) {
        this.likeRepository = likeRepository;
    }

    @Override
    public void likePost(Long userId, Long postId) {

        if (likeRepository.findByUserIdAndPostId(userId, postId).isPresent()) {
            return;
        }

        Like like = new Like();
        like.setUserId(userId);
        like.setPostId(postId);

        try {
            likeRepository.save(like);
        } catch (DataIntegrityViolationException ex) {

        }
    }

    @Override
    public void unlikePost(Long userId, Long postId) {

        likeRepository.findByUserIdAndPostId(userId, postId)
                .ifPresent(likeRepository::delete);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isPostLikedByUser(Long userId, Long postId) {
        return likeRepository.findByUserIdAndPostId(userId, postId).isPresent();
    }

    @Override
    @Transactional(readOnly = true)
    public long getLikeCount(Long postId) {
        return likeRepository.countByPostId(postId);
    }
}
