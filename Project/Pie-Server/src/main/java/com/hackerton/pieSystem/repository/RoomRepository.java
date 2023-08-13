package com.hackerton.pieSystem.repository;

import com.hackerton.pieSystem.domain.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface RoomRepository extends JpaRepository<Room, Long> {
    Room findByRoomId(String roomId);

    @Query("SELECT r FROM Room r WHERE r.no = :no AND r.price = :price AND r.pricePercent = :pricePercent")
    List<Room> findRoomByNoAndPriceAndPricePercent(
            @Param("no") String no,
            @Param("price") String price,
            @Param("pricePercent") Integer pricePercent);
}
