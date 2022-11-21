package com.example.demo.exceptionhandler.customexception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String domain) {
        super(String.format("%s doesn't exist", domain));
    }
}
