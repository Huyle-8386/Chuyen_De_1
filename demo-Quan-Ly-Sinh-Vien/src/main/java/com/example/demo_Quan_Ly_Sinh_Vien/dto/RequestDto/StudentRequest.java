package com.example.demo_Quan_Ly_Sinh_Vien.dto.RequestDto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentRequest {
    private Long id;
    private String name;
    private String email;
}