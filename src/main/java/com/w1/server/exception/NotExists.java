package com.w1.server.exception;

public class NotExists extends RuntimeException {
    public NotExists(String message) {
        super(message);
    }
}
