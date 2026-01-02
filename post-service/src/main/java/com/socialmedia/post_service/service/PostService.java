package com.socialmedia.post_service.service;

import com.socialmedia.post_service.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostService {

    Post createPost(Long authorId,String authorUsername , String content);

    Post getPostById(Long postId);

    Post updatePost(Long postId, Long requesterId, String newContent);

    void deletePost(Long postId, Long requesterId);

    Page<Post> getPostsByUser(Long userId, Pageable pageable);

}
