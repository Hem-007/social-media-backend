package com.socialmedia.follow_service.exception;

public class SelfFollowNotAllowedException extends RuntimeException {
    public SelfFollowNotAllowedException(String message) {
        super(message);
    }
}
