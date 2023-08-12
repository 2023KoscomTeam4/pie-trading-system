package com.hackerton.pieSystem.service;

import com.hackerton.pieSystem.domain.Member;
import com.hackerton.pieSystem.domain.MemberResponseDto;
import com.hackerton.pieSystem.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository repository;


    // GET - 모든 회원 정보 가져오기
    @Transactional(readOnly = true)
    public List<Member> search() {
        // 회원 정보를 저장할 배열
        List<MemberResponseDto> memberResponseDto = new ArrayList<>();
        return repository.findAll();
    }

}
