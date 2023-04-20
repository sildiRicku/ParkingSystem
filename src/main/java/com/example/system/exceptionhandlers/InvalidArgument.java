package com.example.system.exceptionhandlers;

import lombok.Data;

@Data
public class InvalidArgument extends RuntimeException {
    public InvalidArgument(String message) {
        super(message);
    }
}
