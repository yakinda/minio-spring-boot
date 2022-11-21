package com.example.demo.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {

    private String message;
    private ZonedDateTime timestamp;
    private Object data;

    public ErrorResponse(String message) {
        this.message = message;
        this.timestamp = ZonedDateTime.now(ZoneId.of("Z"));
    }

    public ErrorResponse(String message, Object data) {
        this.message = message;
        this.data = data;
        this.timestamp = ZonedDateTime.now(ZoneId.of("Z"));
    }
}
