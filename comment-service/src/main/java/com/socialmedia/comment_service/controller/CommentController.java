package com.socialmedia.comment_service.controller;

import com.socialmedia.comment_service.entity.Comment;
import com.socialmedia.comment_service.dto.CommentResponse;
import com.socialmedia.comment_service.dto.CreateCommentRequest;
import com.socialmedia.comment_service.dto.UpdateCommentRequest;
import com.socialmedia.comment_service.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }


    @PostMapping("/posts/{postId}")
    public CommentResponse createComment(
            @RequestHeader("X-User-Id") Long authorId,
            @RequestHeader("X-Username") String authorUsername,
            @PathVariable Long postId,
            @Valid @RequestBody CreateCommentRequest request) {

        Comment comment = commentService.createComment(
                postId,
                authorId,
                authorUsername,
                request.getContent()
        );

        return mapToResponse(comment);
    }


    @GetMapping("/posts/{postId}")
    public List<CommentResponse> getCommentsByPost(
            @PathVariable Long postId) {

        return commentService.getCommentsByPostId(postId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }


    @PutMapping("/{commentId}")
    public CommentResponse updateComment(
            @RequestHeader("X-User-Id") Long requesterId,
            @PathVariable Long commentId,
            @Valid @RequestBody UpdateCommentRequest request) {

        Comment updated = commentService.updateComment(
                commentId,
                requesterId,
                request.getContent()
        );

        return mapToResponse(updated);
    }


    @DeleteMapping("/{commentId}")
    public void deleteComment(
            @RequestHeader("X-User-Id") Long requesterId,
            @PathVariable Long commentId) {

        commentService.deleteComment(commentId, requesterId);
    }

    @GetMapping("/posts/{postId}/count")
    public long getCommentCount(@PathVariable Long postId) {
        return commentService.getCommentCountByPostId(postId);
    }

    private CommentResponse mapToResponse(Comment comment) {
        return new CommentResponse(
                comment.getId(),
                comment.getPostId(),
                comment.getAuthorId(),
                comment.getAuthorUsername(),
                comment.getContent(),
                comment.getCreatedAt(),
                comment.getUpdatedAt()
        );
    }
}
