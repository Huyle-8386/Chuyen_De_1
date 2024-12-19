package com.example.demo_Quan_Ly_Sinh_Vien.exception;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BlogAPIException extends RuntimeException{

    private String message;
    private HttpStatus status;
}
