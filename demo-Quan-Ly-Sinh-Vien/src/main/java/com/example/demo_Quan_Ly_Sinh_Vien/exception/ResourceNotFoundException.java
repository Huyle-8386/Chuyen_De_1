package com.example.demo_Quan_Ly_Sinh_Vien.exception;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ResourceNotFoundException extends RuntimeException{

    private String resourceName;
    private String filedName;
    private Long filedValue;

    public ResourceNotFoundException(String resourceName, String filedName, Long filedValue){
        super(String.format("$ not found with $: '$'",resourceName,filedName,filedValue));
        this.resourceName = resourceName;
        this.filedName = filedName;
        this.filedValue = filedValue;
    }
}
