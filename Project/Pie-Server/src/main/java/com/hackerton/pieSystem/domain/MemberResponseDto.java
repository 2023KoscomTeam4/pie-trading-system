package com.hackerton.pieSystem.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberResponseDto {

    private Long id;
    private String name;
    private String username;
    private String password;
    private int age;

}
