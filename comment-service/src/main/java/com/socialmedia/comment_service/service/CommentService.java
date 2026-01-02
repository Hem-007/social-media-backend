package com.socialmedia.comment_service.service;


import com.socialmedia.comment_service.entity.Comment;

import java.util.List;

public interface CommentService {

    Comment createComment(
            Long postId,
            Long authorId,
            String authorUsername,
            String content
    );

    List<Comment> getCommentsByPostId(Long postId);

    Comment updateComment(
            Long commentId,
            Long requesterId,
            String newContent
    );

    void deleteComment(
            Long commentId,
            Long requesterId
    );

    long getCommentCountByPostId(Long postId);

}
