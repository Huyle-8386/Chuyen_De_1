package com.example.demo_Quan_Ly_Sinh_Vien.service;

import com.example.demo_Quan_Ly_Sinh_Vien.dto.RequestDto.StudentRequest;
import com.example.demo_Quan_Ly_Sinh_Vien.dto.ResponseDto.StudentResponse;
import com.example.demo_Quan_Ly_Sinh_Vien.entity.Student;

import java.util.List;

public interface StudentService {
    void createStudent(StudentRequest request);
    void updateStudentById(Long id, StudentRequest request);
    void deleteStudentById(Long id);
    List<StudentResponse> getAll();
    List<Student> searchStudents(String query);

}
