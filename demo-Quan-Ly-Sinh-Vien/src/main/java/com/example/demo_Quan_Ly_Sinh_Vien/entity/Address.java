package com.example.demo_Quan_Ly_Sinh_Vien.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;
    private String nameStreet;
    private String country;
    private String homeNumber;
}
