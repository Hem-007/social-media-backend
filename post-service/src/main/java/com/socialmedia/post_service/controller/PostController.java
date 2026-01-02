package com.socialmedia.post_service.controller;


import com.socialmedia.post_service.dto.PostResponse;
import com.socialmedia.post_service.dto.UpdatePostRequest;
import com.socialmedia.post_service.dto.createPostRequest;
import com.socialmedia.post_service.entity.Post;
import com.socialmedia.post_service.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;


    @PostMapping
    public PostResponse createPost(
            @RequestHeader("X-User-Id") Long authorId,
            @RequestHeader("X-Username") String authorUsername,
            @RequestBody createPostRequest request){
        Post post = postService.createPost(authorId,authorUsername, request.getContent());
        return mapToResponse(post);
    }

    @GetMapping("/{postId}")
    public PostResponse getPostById(@PathVariable Long postId){
        return mapToResponse(postService.getPostById(postId));
    }

    @PutMapping("/{postId}")
    public PostResponse updatePost(@PathVariable Long postId,
                                   @RequestHeader("X-User-Id") Long requesterId,
                                   @RequestBody UpdatePostRequest request){
        Post updatedPost = postService.updatePost(postId, requesterId, request.getContent());
        return mapToResponse(updatedPost);
    }

    @DeleteMapping("/{postId}")
    public void deletePost(@PathVariable Long postId,
                           @RequestHeader("X-User-Id") Long requesterId){
        postService.deletePost(postId, requesterId);
    }

    @GetMapping("/users/{userId}")
    public Page<PostResponse> getPostsByUser(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);

        return postService.getPostsByUser(userId, pageable)
                .map(this::mapToResponse);
    }

    private PostResponse mapToResponse(Post post) {
        PostResponse response = new PostResponse();
        response.setId(post.getId());
        response.setAuthorId(post.getAuthorId());
        response.setAuthorUsername(post.getAuthorUsername());
        response.setContent(post.getContent());
        response.setCreatedAt(post.getCreatedAt());
        response.setUpdatedAt(post.getUpdatedAt());
        return response;
    }

}
