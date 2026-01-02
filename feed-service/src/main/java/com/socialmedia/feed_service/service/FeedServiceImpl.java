package com.socialmedia.feed_service.service;

import com.socialmedia.feed_service.dto.*;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class FeedServiceImpl implements FeedService {

    private final WebClient.Builder webClientBuilder;

    public FeedServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    @Override
    public FeedResponse getUserFeed(Long userId, int page, int size) {

        /* ============================
           1. Get followed user IDs
           ============================ */
        List<Long> followedUserIds = webClientBuilder.build()
                .get()
                .uri("http://FOLLOW-SERVICE/follows/following/{userId}", userId)
                .retrieve()
                .bodyToFlux(Long.class)
                .collectList()
                .block();

        if (followedUserIds == null || followedUserIds.isEmpty()) {
            return new FeedResponse(page, size, List.of());
        }

        /* ============================
           2. Fetch posts from Post Service
           ============================ */
        List<FeedPostResponse> allPosts = new ArrayList<>();

        for (Long followedUserId : followedUserIds) {

            PostPageResponse pageResponse = webClientBuilder.build()
                    .get()
                    .uri(uriBuilder ->
                            uriBuilder
                                    .scheme("http")
                                    .host("POST-SERVICE")
                                    .path("/posts/users/{userId}")
                                    .queryParam("page", 0)
                                    .queryParam("size", size)
                                    .build(followedUserId))
                    .retrieve()
                    .bodyToMono(PostPageResponse.class)
                    .block();

            if (pageResponse != null && pageResponse.getContent() != null) {
                for (PostSummaryResponse post : pageResponse.getContent()) {

                    FeedPostResponse feedPost = new FeedPostResponse();
                    feedPost.setPostId(post.getPostId());
                    feedPost.setAuthorId(post.getAuthorId());
                    feedPost.setAuthorUsername(post.getAuthorUsername());
                    feedPost.setContent(post.getContent());
                    feedPost.setCreatedAt(post.getCreatedAt());

                    // defaults (important)
                    feedPost.setLikeCount(0);
                    feedPost.setCommentCount(0);
                    feedPost.setLikedByMe(false);

                    allPosts.add(feedPost);
                }
            }
        }

        /* ============================
           3. Enrich with likes & comments
           ============================ */
        for (FeedPostResponse post : allPosts) {

            // Like count
            Long likeCount = webClientBuilder.build()
                    .get()
                    .uri("http://LIKE-SERVICE/likes/posts/{postId}/count",
                            post.getPostId())
                    .retrieve()
                    .bodyToMono(Long.class)
                    .block();

            post.setLikeCount(likeCount != null ? likeCount : 0);

            // Liked by current user
            Boolean likedByMe = webClientBuilder.build()
                    .get()
                    .uri("http://LIKE-SERVICE/likes/posts/{postId}/status",
                            post.getPostId())
                    .header("X-User-Id", userId.toString())
                    .retrieve()
                    .bodyToMono(Boolean.class)
                    .block();

            post.setLikedByMe(Boolean.TRUE.equals(likedByMe));

            // Comment count
            Long commentCount = webClientBuilder.build()
                    .get()
                    .uri("http://COMMENT-SERVICE/comments/posts/{postId}/count",
                            post.getPostId())
                    .retrieve()
                    .bodyToMono(Long.class)
                    .block();

            post.setCommentCount(commentCount != null ? commentCount : 0);
        }

        /* ============================
           4. Sort by newest first
           ============================ */
        allPosts.sort(
                Comparator.comparing(FeedPostResponse::getCreatedAt).reversed()
        );

        /* ============================
           5. Pagination
           ============================ */
        int fromIndex = Math.min(page * size, allPosts.size());
        int toIndex = Math.min(fromIndex + size, allPosts.size());

        List<FeedPostResponse> pagePosts =
                allPosts.subList(fromIndex, toIndex);

        return new FeedResponse(page, size, pagePosts);
    }
}
