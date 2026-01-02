package com.socialmedia.follow_service.model;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "follows",
        uniqueConstraints = {
            @UniqueConstraint(
                    name = "uk_follower_followee",
                    columnNames = {"followerId", "followeeId"}
            )
        },
        indexes = {
            @Index(name = "idx_follower_id", columnList = "followerId"),
            @Index(name = "idx_followee_id", columnList = "followeeId")
        }
)

public class Follow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "follower_id" ,nullable = false)
    private Long followerId;

    @Column(name = "followee_id",nullable = false)
    private Long followeeId;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFollowerId() {
        return followerId;
    }

    public void setFollowerId(Long followerId) {
        this.followerId = followerId;
    }

    public Long getFolloweeId() {
        return followeeId;
    }

    public void setFolloweeId(Long followeeId) {
        this.followeeId = followeeId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Follow(Long id, Long followerId, Long followeeId, LocalDateTime createdAt) {
        this.id = id;
        this.followerId = followerId;
        this.followeeId = followeeId;
        this.createdAt = createdAt;
    }

    public Follow() {
    }
}
