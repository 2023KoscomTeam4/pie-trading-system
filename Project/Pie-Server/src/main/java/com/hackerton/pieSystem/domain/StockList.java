package com.hackerton.pieSystem.domain;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class StockList {

    private @Id
    @GeneratedValue
    @Column(name = "member_id") Long id;
    private String name;
    private String username;
    private String password;
    private int age;
}