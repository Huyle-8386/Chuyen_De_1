package com.example.demo_Quan_Ly_Sinh_Vien.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class ErrorDetail {

    private Date timestameDate;
    private String message;
    private String details;
}
