package com.example.demo_Quan_Ly_Sinh_Vien.dto.ResponseDto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentResponse {
    private Long id;
    private String name;
    private String email;
    private int phone;
}
