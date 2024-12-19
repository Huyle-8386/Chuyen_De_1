package com.example.demo_Quan_Ly_Sinh_Vien.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalHandlerException {

    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetail> handlerResourceNotFoundException(ResourceNotFoundException exception, WebRequest request){

        ErrorDetail errorDetail = ErrorDetail.builder()
                .timestameDate(new Date())
                .message(exception.getMessage())
                .details(request.getDescription(false))
                .build();

        return new ResponseEntity<>(errorDetail, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = BlogAPIException.class)
    public ResponseEntity<ErrorDetail> handlerBlogAPIException(BlogAPIException exception, WebRequest request){
        ErrorDetail errorDetail = ErrorDetail.builder()
                .timestameDate(new Date())
                .message(exception.getMessage())
                .details(request.getDescription(false))
                .build();

        return new ResponseEntity<>(errorDetail, HttpStatus.BAD_REQUEST);
    }
}
