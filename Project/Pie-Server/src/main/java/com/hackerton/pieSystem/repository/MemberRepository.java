package com.hackerton.pieSystem.repository;

import com.hackerton.pieSystem.domain.KospiStock;
import com.hackerton.pieSystem.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {

    default Member saveWithTimestamp(Member entity) {
        // 현재 시간 설정
        entity.setCreatedAt(LocalDateTime.now());
        // save 호출
        return save(entity);
    }
}
