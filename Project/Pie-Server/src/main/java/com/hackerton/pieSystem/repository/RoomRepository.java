package com.hackerton.pieSystem.repository;

import com.hackerton.pieSystem.domain.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
    Room findByRoomId(String roomId);
}
