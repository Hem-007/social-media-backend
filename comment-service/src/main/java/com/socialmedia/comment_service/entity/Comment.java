package com.socialmedia.comment_service.entity;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "comments",
        indexes = {
                @Index(name = "idx_post_id", columnList = "post_id"),
                @Index(name = "idx_author_id", columnList = "author_id")
        }
)
public class Comment {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name = "post_id", nullable = false)
        private Long postId;

        @Column(name = "author_id", nullable = false, updatable = false)
        private Long authorId;

        @Column(name = "author_username", nullable = false, length = 100)
        private String authorUsername;

        @Column(nullable = false, length = 1000)
        private String content;

        @Column(name = "created_at", nullable = false, updatable = false)
        private LocalDateTime createdAt;

        @Column(name = "updated_at", nullable = false)
        private LocalDateTime updatedAt;


        @Column(name = "is_deleted", nullable = false)
        private boolean isDeleted = false;

        @PrePersist
        protected void onCreate() {
                this.createdAt = LocalDateTime.now();
                this.updatedAt = this.createdAt;
        }

        @PreUpdate
        protected void onUpdate() {
                this.updatedAt = LocalDateTime.now();
        }

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public Long getPostId() {
                return postId;
        }

        public void setPostId(Long postId) {
                this.postId = postId;
        }

        public Long getAuthorId() {
                return authorId;
        }

        public void setAuthorId(Long authorId) {
                this.authorId = authorId;
        }

        public String getAuthorUsername() {
                return authorUsername;
        }

        public void setAuthorUsername(String authorUsername) {
                this.authorUsername = authorUsername;
        }

        public String getContent() {
                return content;
        }

        public void setContent(String content) {
                this.content = content;
        }

        public LocalDateTime getCreatedAt() {
                return createdAt;
        }

        public void setCreatedAt(LocalDateTime createdAt) {
                this.createdAt = createdAt;
        }

        public LocalDateTime getUpdatedAt() {
                return updatedAt;
        }

        public void setUpdatedAt(LocalDateTime updatedAt) {
                this.updatedAt = updatedAt;
        }

        public boolean isDeleted() {
                return isDeleted;
        }

        public void setDeleted(boolean deleted) {
                isDeleted = deleted;
        }

        public Comment(Long id, Long postId, Long authorId, String authorUsername, String content, LocalDateTime createdAt, LocalDateTime updatedAt, boolean isDeleted) {
                this.id = id;
                this.postId = postId;
                this.authorId = authorId;
                this.authorUsername = authorUsername;
                this.content = content;
                this.createdAt = createdAt;
                this.updatedAt = updatedAt;
                this.isDeleted = isDeleted;
        }

        public Comment() {
        }
}
