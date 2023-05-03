package com.example.system.exceptionhandlers;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;


@Data
@AllArgsConstructor
public class ApiError {
    private String message;
    private String timeStamp;
    private HttpStatus status;
}
