package com.hackerton.pieSystem.domain;


import lombok.Data;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
public class Member {

    private @Id
    @GeneratedValue
    @Column(name = "member_id") Long id;
    private String name;
    private String username;
    private String password;
    private String grade;
    private int age;
    private String job;
    private String investOpt;
    private int salary;
    private int property;
    private String profile;
}