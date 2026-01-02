package com.socialmedia.feed_service.dto;

import java.util.List;

public class PostPageResponse {

    private List<PostSummaryResponse> content;

    public List<PostSummaryResponse> getContent() {
        return content;
    }

    public void setContent(List<PostSummaryResponse> content) {
        this.content = content;
    }
}
