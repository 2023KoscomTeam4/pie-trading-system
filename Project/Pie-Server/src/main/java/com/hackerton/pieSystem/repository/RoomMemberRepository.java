package com.hackerton.pieSystem.repository;

import com.hackerton.pieSystem.domain.Member;
import com.hackerton.pieSystem.domain.Room;
import com.hackerton.pieSystem.domain.RoomMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface RoomMemberRepository extends JpaRepository<RoomMember, Long> {
    List<RoomMember> findByUserId(String userId);
    List<RoomMember> findByRoomId(String roomId);
    RoomMember findById(String roomMemberId);
    RoomMember findByUserIdAndId(String userId, String roomMemberId);
    List<RoomMember> findByUserIdAndContractCmpYn(String userId, String contractCmpYn);
    List<RoomMember> findByRoomIdAndContractCmpYn(String roomId, String contractCmpYn);
    default RoomMember saveWithTimestamp(RoomMember entity) {
        // 현재 시간 설정
        entity.setCreatedAt(LocalDateTime.now());
        // save 호출
        return save(entity);
    }


}
