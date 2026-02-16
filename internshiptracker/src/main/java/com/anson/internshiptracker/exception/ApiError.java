package com.anson.internshiptracker.exception;

import java.time.Instant;

public class ApiError {
    
    private final int status;
    private final String message;
    private final Instant timestamp = Instant.now();
    
    public ApiError(int status, String message) {
        this.status = status;
        this.message = message;
    }
    
    public int getStatus() {
        return status;
    }
    
    public String getMessage() {
        return message;
    }
    
    public Instant getTimestamp() {
        return timestamp;
    }
}
