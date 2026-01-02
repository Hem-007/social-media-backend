package com.socialmedia.feed_service.controller;

import com.socialmedia.feed_service.dto.FeedResponse;
import com.socialmedia.feed_service.service.FeedService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/feed")
public class FeedController {

    private final FeedService feedService;

    public FeedController(FeedService feedService) {
        this.feedService = feedService;
    }

    @GetMapping
    public FeedResponse getFeed(
            @RequestHeader("X-User-Id") Long userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return feedService.getUserFeed(userId, page, size);
    }
}
