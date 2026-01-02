package com.socialmedia.feed_service.dto;

import java.util.List;

public class FeedResponse {

    private int page;
    private int size;
    private List<FeedPostResponse> posts;

    public FeedResponse(int page, int size, List<FeedPostResponse> posts) {
        this.page = page;
        this.size = size;
        this.posts = posts;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<FeedPostResponse> getPosts() {
        return posts;
    }

    public void setPosts(List<FeedPostResponse> posts) {
        this.posts = posts;
    }
}
