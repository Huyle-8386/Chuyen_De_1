package com.example.demo_Quan_Ly_Sinh_Vien.service.serviceImpl;

import com.example.demo_Quan_Ly_Sinh_Vien.entity.ConnectInfo;
import com.example.demo_Quan_Ly_Sinh_Vien.service.ConnectService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ConnectionServiceImpl implements ConnectService {

    private final List<ConnectInfo> connections = Collections.synchronizedList(new ArrayList<>());
    @Override
    public void addConnection(String ipAddress, int port) {
        connections.add(new ConnectInfo(1L,ipAddress, port));
    }

    @Override
    public List<ConnectInfo> getActiveConnections() {
        return new ArrayList<>(connections);
    }
}
