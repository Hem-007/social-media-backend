package com.socialmedia.feed_service.service;

import com.socialmedia.feed_service.dto.FeedResponse;

public interface FeedService {

    FeedResponse getUserFeed(Long userId, int page, int size);
}
