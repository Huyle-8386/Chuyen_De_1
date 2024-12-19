package com.example.demo_Quan_Ly_Sinh_Vien.service;

import com.example.demo_Quan_Ly_Sinh_Vien.entity.ConnectInfo;

import java.util.List;

public interface ConnectService {
    void addConnection(String ipAddress, int port);
    List<ConnectInfo> getActiveConnections();
}
