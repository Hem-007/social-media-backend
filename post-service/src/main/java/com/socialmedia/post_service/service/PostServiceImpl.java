package com.socialmedia.post_service.service;

import com.socialmedia.post_service.entity.Post;
import com.socialmedia.post_service.exception.PostNotFoundException;
import com.socialmedia.post_service.exception.UnauthorizedPostAccessException;
import com.socialmedia.post_service.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService{

    @Autowired
    private PostRepository postRepository;


    @Override
    public Post createPost(Long authorId, String authorUsername, String content) {

        Post post = new Post();
        post.setAuthorId(authorId);
        post.setAuthorUsername(authorUsername);
        post.setContent(content);

        return postRepository.save(post);
    }

    @Override
    public Post getPostById(Long postId) {


        return postRepository.findByIdAndIsDeletedFalse(postId)
                .orElseThrow(() -> new PostNotFoundException("Post not found with id: " + postId));

    }

    @Override
    public Post updatePost(Long postId, Long requesterId, String newContent) {

        Post post = postRepository.findByIdAndIsDeletedFalse(postId)
                .orElseThrow(() -> new PostNotFoundException("Post not found with id: " + postId));

        if(!post.getAuthorId().equals(requesterId)){
            throw  new UnauthorizedPostAccessException("Unauthorized access to update the post.");
        }

        post.setContent(newContent);
        return postRepository.save(post);
    }

    @Override
    public void deletePost(Long postId, Long requesterId) {

        Post post = postRepository.findByIdAndIsDeletedFalse(postId)
                .orElseThrow(() -> new PostNotFoundException("Post not found with id: " + postId));

        if(!post.getAuthorId().equals(requesterId)){
            throw  new UnauthorizedPostAccessException("Unauthorized access to update the post.");
        }

        post.setDeleted(true);
        postRepository.save(post);
    }

    @Override
    public Page<Post> getPostsByUser(Long userId, Pageable pageable) {
        return postRepository.findByAuthorIdAndIsDeletedFalse(userId, pageable);

    }
}
