package com.booking.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super("Not Found");
    }

    public ResourceNotFoundException(String user, String id, Long id1) {

    }
}
