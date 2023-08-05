package com.hackerton.pieSystem.domain;


import jakarta.persistence.Entity;
import lombok.Data;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Data
@Entity
public class Member {

    private @Id
    @GeneratedValue
    @Column(name = "member_id") Long id;
    private String name;
    private String username;
    private String password;
    private int age;
}