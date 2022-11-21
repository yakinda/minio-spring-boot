package com.example.demo.factory;

import com.example.demo.dto.APIResponse;
import com.example.demo.exceptionhandler.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public interface ResponseFactory {
    static <T> ResponseEntity<APIResponse<T>> success(T result) {
        APIResponse<T> response = new APIResponse<>();
        HttpStatus successStatus = HttpStatus.OK;
        response.setStatus(successStatus.name());
        response.setResult(result);
        return ResponseEntity.status(successStatus).body(response);
    }

    static ResponseEntity<APIResponse<Object>> success() {
        APIResponse<Object> response = new APIResponse<>();
        HttpStatus successStatus = HttpStatus.OK;
        response.setStatus(successStatus.name());
        return ResponseEntity.status(successStatus).body(response);
    }

    static ResponseEntity<APIResponse<Object>> error(HttpStatus status, ErrorResponse error) {
        APIResponse<Object> response = new APIResponse<>();
        response.setStatus(status.name());
        response.setError(error);
        return ResponseEntity.status(status).body(response);
    }
}
