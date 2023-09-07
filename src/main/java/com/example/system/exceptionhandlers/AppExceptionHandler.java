package com.example.system.exceptionhandlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleParkingNotFound(NotFoundException ex, WebRequest request) {
        return new ResponseEntity<>(new ApiError(ex.getMessage(), LocalDateTime.now().format(formatter), HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidArgument.class)
    public ResponseEntity<Object> handleInvalidArguments(InvalidArgument ex, WebRequest request) {
        return new ResponseEntity<>(new ApiError(ex.getMessage(), LocalDateTime.now().format(formatter), HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
    }

//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public Map<String, String> handleInvalidMethodArgument(MethodArgumentNotValidException ex) {
//        Map<String, String> errorMap = new HashMap<>();
//        ex.getBindingResult().getFieldErrors().forEach(error ->
//                errorMap.put(error.getField(), error.getDefaultMessage())
//        );
//        return errorMap;
//    }
}
