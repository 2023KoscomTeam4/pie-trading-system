package com.hackerton.pieSystem.service;

import com.hackerton.pieSystem.domain.Member;
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
        List<Member> memberSearchResponseDto = new ArrayList<Member>();
        List<Member> members = repository.findAll();

        for(Member member : members) {

            Member dto = new Member();
            dto.setId(member.getId());
            dto.setName(member.getName());
            dto.setUsername(member.getUsername());
            dto.setPassword(member.getPassword());
            dto.setGrade(member.getGrade());
            dto.setAge(member.getAge());
            dto.setJob(member.getJob());
            dto.setInvestOpt(member.getInvestOpt());
            dto.setSalary(member.getSalary());
            dto.setProperty(member.getProperty());
            dto.setProfile(member.getProfile());

            memberSearchResponseDto.add(dto);

        }

        return memberSearchResponseDto;
    }
}
