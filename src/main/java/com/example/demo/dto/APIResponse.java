package com.example.demo.dto;

import com.example.demo.exceptionhandler.ErrorResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class APIResponse<T> {
    private String status;
    private T result;
    private ErrorResponse error;
}
