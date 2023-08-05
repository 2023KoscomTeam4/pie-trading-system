package com.hackerton.pieSystem.controller;


import com.hackerton.pieSystem.domain.Member;
import com.hackerton.pieSystem.repository.MemberRepository;
import com.hackerton.pieSystem.service.MemberService;
import com.hackerton.pieSystem.service.MemberService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MemberController {

    private final MemberRepository repository;

    private final MemberService memberService;

    MemberController(MemberRepository repository, MemberService memberService) {
        this.repository = repository;
        this.memberService = memberService;
    }

    // SEARCH
    @GetMapping("/api/member/search")
    public List<Member> search() {
        return memberService.search();
    }

}