package com.example.demo_Quan_Ly_Sinh_Vien.service.serviceImpl;

import com.example.demo_Quan_Ly_Sinh_Vien.dto.RequestDto.StudentRequest;
import com.example.demo_Quan_Ly_Sinh_Vien.dto.ResponseDto.StudentResponse;
import com.example.demo_Quan_Ly_Sinh_Vien.entity.Student;
import com.example.demo_Quan_Ly_Sinh_Vien.exception.ResourceNotFoundException;
import com.example.demo_Quan_Ly_Sinh_Vien.repository.StudentRepository;
import com.example.demo_Quan_Ly_Sinh_Vien.service.StudentService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {

    private StudentRepository studentRepository;
    private ModelMapper modelMapper;

    @Override
    public void createStudent(StudentRequest request) {
        Student student = modelMapper.map(request,Student.class);
        studentRepository.save(student);

        modelMapper.map(student, StudentResponse.class);
    }

    @Override
    public void updateStudentById(Long id, StudentRequest request) {
        Student student = studentRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Student", "id" , id)
        );
        student.setName(request.getName());
        student.setEmail(request.getEmail());
        studentRepository.save(student);

        modelMapper.map(student, StudentResponse.class);
    }

    @Override
    public void deleteStudentById(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Student", "id" , id)
        );
        studentRepository.delete(student);
    }

    @Override
    public List<StudentResponse> getAll() {
        List<Student> students = studentRepository.findAll();

        return students.stream()
                .map((student) -> modelMapper.map(student,StudentResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<Student> searchStudents(String query) {
        return studentRepository.findByNameContainingIgnoreCaseOrEmailContainingIgnoreCase(query,query);
    }
}
