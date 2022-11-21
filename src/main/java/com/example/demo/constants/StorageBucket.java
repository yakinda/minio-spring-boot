package com.example.demo.constants;

public enum StorageBucket {
    FILE("file"),

    IMAGE("image");

    private final String value;

    StorageBucket(String value) {
        this.value = value;
    }

    public String value() {
        return this.value;
    }
}
