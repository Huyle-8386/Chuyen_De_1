package com.example.demo_Quan_Ly_Sinh_Vien.security;

import com.example.demo_Quan_Ly_Sinh_Vien.entity.User;
import com.example.demo_Quan_Ly_Sinh_Vien.exception.BlogAPIException;
import com.example.demo_Quan_Ly_Sinh_Vien.exception.ResourceNotFoundException;
import com.example.demo_Quan_Ly_Sinh_Vien.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomerUserDetailService implements UserDetailsService {

    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        System.out.println(username);
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new RuntimeException("User not found with username: " + username)
        );

        Set<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getRoleName()))
                .collect(Collectors.toSet());

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                authorities
        );
    }
}
