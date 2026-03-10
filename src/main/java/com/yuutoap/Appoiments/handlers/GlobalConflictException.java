package com.yuutoap.Appoiments.handlers;

public class GlobalConflictException extends RuntimeException{
    public GlobalConflictException(String message) {
        super(message);
    }
}
