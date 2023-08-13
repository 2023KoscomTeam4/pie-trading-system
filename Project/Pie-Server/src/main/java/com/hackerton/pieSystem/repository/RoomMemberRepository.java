package com.hackerton.pieSystem.repository;

import com.hackerton.pieSystem.domain.Room;
import com.hackerton.pieSystem.domain.RoomMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomMemberRepository extends JpaRepository<RoomMember, Long> {
    List<RoomMember> findByUserId(String userId);
    List<RoomMember> findByRoomId(String roomId);
    RoomMember findByUserIdAndId(String userId, String roomMemberId);


}
