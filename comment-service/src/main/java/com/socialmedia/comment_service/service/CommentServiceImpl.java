package com.socialmedia.comment_service.service;

import com.socialmedia.comment_service.entity.Comment;
import com.socialmedia.comment_service.exception.CommentNotFoundException;
import com.socialmedia.comment_service.exception.UnauthorizedCommentAccessException;
import com.socialmedia.comment_service.repository.CommentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CommentServiceImpl implements CommentService{


    private final CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public Comment createComment(Long postId, Long authorId, String authorUsername, String content) {

        Comment comment = new Comment();

        comment.setPostId(postId);
        comment.setAuthorId(authorId);
        comment.setAuthorUsername(authorUsername);
        comment.setContent(content);

        return commentRepository.save(comment);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Comment> getCommentsByPostId(Long postId) {

        return commentRepository.findByPostIdAndIsDeletedFalseOrderByCreatedAtAsc(postId);
    }

    @Override
    public Comment updateComment(Long commentId, Long requesterId, String newContent) {

        Comment comment = commentRepository.findByIdAndIsDeletedFalse(commentId)
                .orElseThrow(() -> new CommentNotFoundException("Comment Not Found with id: " + commentId));

        if(!comment.getAuthorId().equals(requesterId)){
            throw new UnauthorizedCommentAccessException("Unauthorized Access to Comment with id: " + commentId);
        }
        comment.setContent(newContent);

        return commentRepository.save(comment);
    }

    @Override
    public void deleteComment(Long commentId, Long requesterId) {

        Comment comment = commentRepository
                .findByIdAndIsDeletedFalse(commentId)
                .orElseThrow(() ->
                        new CommentNotFoundException(
                                "Comment not found with id: " + commentId));

        if (!comment.getAuthorId().equals(requesterId)) {
            throw new UnauthorizedCommentAccessException(
                    "You are not allowed to delete this comment");
        }

        comment.setDeleted(true);
        commentRepository.save(comment);
    }

    @Override
    public long getCommentCountByPostId(Long postId) {
        return commentRepository.countByPostIdAndIsDeletedFalse(postId);
    }
}
