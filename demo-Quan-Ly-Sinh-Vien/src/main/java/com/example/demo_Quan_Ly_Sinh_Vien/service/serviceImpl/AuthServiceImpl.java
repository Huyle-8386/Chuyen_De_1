package com.example.demo_Quan_Ly_Sinh_Vien.service.serviceImpl;

import com.example.demo_Quan_Ly_Sinh_Vien.dto.RequestDto.LoginDto;
import com.example.demo_Quan_Ly_Sinh_Vien.dto.RequestDto.RegisterDto;
import com.example.demo_Quan_Ly_Sinh_Vien.entity.Role;
import com.example.demo_Quan_Ly_Sinh_Vien.entity.User;
import com.example.demo_Quan_Ly_Sinh_Vien.exception.BlogAPIException;
import com.example.demo_Quan_Ly_Sinh_Vien.repository.RoleRepository;
import com.example.demo_Quan_Ly_Sinh_Vien.repository.UserRepository;
import com.example.demo_Quan_Ly_Sinh_Vien.service.AuthService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private ModelMapper modelMapper;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;


    @Override
    public boolean login(LoginDto loginDto) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginDto.getUsername(),
                            loginDto.getPassword()
                    )
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean register(RegisterDto registerDto) {
        if (userRepository.existsByUsername(registerDto.getUsername()) && userRepository.existsByEmail(registerDto.getEmail())){
            throw BlogAPIException.builder()
                    .message("UserName or Email is exited!")
                    .build();
        }
        User user = modelMapper.map(registerDto, User.class);
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        Set<Role> roles = new HashSet<>();
        Role role = roleRepository.findByRoleName("ROLE_USER").get();
        roles.add(role);
        user.setRoles(roles);
        userRepository.save(user);

        return true;
    }
}
