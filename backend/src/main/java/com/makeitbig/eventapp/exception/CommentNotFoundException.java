package com.makeitbig.eventapp.exception;

public class CommentNotFoundException extends RuntimeException {

    public CommentNotFoundException(String message) {
        super(message);
    }
}
