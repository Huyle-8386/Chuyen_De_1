package com.example.demo_Quan_Ly_Sinh_Vien.dto.ResponseDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class JwtAuthResponseDto {
    private String accessToken;
    private String tokenType = "Bearer";  // Default value set once

    // Constructor that only requires the accessToken, tokenType is set by default
    public JwtAuthResponseDto(String accessToken) {
        this.accessToken = accessToken;
    }
}
