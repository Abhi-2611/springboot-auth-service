package com.example.app.backend.common.exception;

public class DuplicateResourceException extends RuntimeException {
    
    public DuplicateResourceException(String message){
        super(message);
    }
}
