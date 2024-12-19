package com.example.demo_Quan_Ly_Sinh_Vien.repository;

import com.example.demo_Quan_Ly_Sinh_Vien.entity.ConnectInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConnectionInfoRepository extends JpaRepository<ConnectInfo, Long> {
}
