package com.example.demo_Quan_Ly_Sinh_Vien.controller;

import com.example.demo_Quan_Ly_Sinh_Vien.dto.RequestDto.LoginDto;
import com.example.demo_Quan_Ly_Sinh_Vien.dto.RequestDto.RegisterDto;
import com.example.demo_Quan_Ly_Sinh_Vien.dto.RequestDto.StudentRequest;
import com.example.demo_Quan_Ly_Sinh_Vien.dto.ResponseDto.StudentResponse;
import com.example.demo_Quan_Ly_Sinh_Vien.entity.ConnectInfo;
import com.example.demo_Quan_Ly_Sinh_Vien.entity.Student;
import com.example.demo_Quan_Ly_Sinh_Vien.service.AuthService;
import com.example.demo_Quan_Ly_Sinh_Vien.service.ConnectService;
import com.example.demo_Quan_Ly_Sinh_Vien.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
public class StudentController {

    private StudentService service;
    private final AuthService authService;

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String showRegisterPage() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute RegisterDto registerDto, Model model) {
        boolean isRegister = authService.register(registerDto);

        if (isRegister) {
            return "redirect:/students";
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "register";
        }
    }

    @PostMapping("/logout")
    public String logout() {
        return "login";
    }

    @PostMapping("/login")
    public String login(LoginDto loginDto, Model model) {
        System.out.println("Nhận yêu cầu đăng nhập với username: " + loginDto.getUsername());
        boolean checkLogin = authService.login(loginDto);
        if (checkLogin) {
            System.out.println("Đăng nhập thành công");
            return "redirect:/students";
        } else {
            System.out.println("Đăng nhập thất bại");
            return "redirect:/login";
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    @GetMapping("/students")
    public String homePage(Model model){
        List<StudentResponse> students = service.getAll();
        model.addAttribute("students", students);
        System.out.println("Truy cập trang home.html");
        return "home";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/students/create")
    public String saveStudent(@ModelAttribute("student") StudentRequest student) {
        service.createStudent(student);
        return "redirect:/students";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/students/update/{id}")
    public String updateStudent(@PathVariable Long id, @ModelAttribute("student") StudentRequest student) {
        service.updateStudentById(id, student);
        return "redirect:/students";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/students/delete/{id}")
    public ResponseEntity<String> deleteStudentById(@PathVariable Long id, @ModelAttribute("student") StudentRequest student){
        service.deleteStudentById(id);
        return new ResponseEntity<>("redirect:/students!", HttpStatus.NO_CONTENT);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    @GetMapping("/students/search")
    public String searchStudents(@RequestParam("query") String query, Model model) {
        List<Student> students = service.searchStudents(query);
        model.addAttribute("students", students);
        return "home";
    }

}
