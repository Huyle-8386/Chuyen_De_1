package com.example.demo_Quan_Ly_Sinh_Vien.service;

import com.example.demo_Quan_Ly_Sinh_Vien.dto.RequestDto.LoginDto;
import com.example.demo_Quan_Ly_Sinh_Vien.dto.RequestDto.RegisterDto;

public interface AuthService {
    boolean login(LoginDto loginDto);
    boolean register(RegisterDto registerDto);
}
