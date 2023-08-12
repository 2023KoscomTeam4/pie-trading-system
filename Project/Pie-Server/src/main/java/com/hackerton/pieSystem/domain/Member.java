package com.hackerton.pieSystem.domain;


import jakarta.persistence.Entity;
import lombok.Data;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Data
@Entity
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "user_id") Long id;
    private String userName;
    private String passWord;
}