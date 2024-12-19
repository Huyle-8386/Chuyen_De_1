package com.example.demo_Quan_Ly_Sinh_Vien.repository;

import com.example.demo_Quan_Ly_Sinh_Vien.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByNameContainingIgnoreCaseOrEmailContainingIgnoreCase(String name, String email);
}
