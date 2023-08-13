package com.hackerton.pieSystem.service;

import com.hackerton.pieSystem.domain.ChatRoom;
import com.hackerton.pieSystem.domain.Room;
import com.hackerton.pieSystem.domain.RoomMember;
import com.hackerton.pieSystem.domain.RoomMemberResponseDto;
import com.hackerton.pieSystem.repository.KospiStockRepository;
import com.hackerton.pieSystem.repository.RoomMemberRepository;
import com.hackerton.pieSystem.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChatService {

    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private RoomMemberRepository roomMemberRepository;
    private Map<String, ChatRoom> chatRooms;

    @PostConstruct
    //의존관게 주입완료되면 실행되는 코드
    private void init() {
        chatRooms = new LinkedHashMap<>();
    }

    //채팅방 불러오기
    public List<RoomMemberResponseDto> findAllRoom(String userId) {
//        //채팅방 최근 생성 순으로 반환
//        List<ChatRoom> result = new ArrayList<>(chatRooms.values());
//        Collections.reverse(result);

        List<RoomMember> roomMembers = roomMemberRepository.findByUserId(userId);
        List<RoomMemberResponseDto> result = new ArrayList<>();

        for (RoomMember roomMember : roomMembers) {
            Room room = roomRepository.findByRoomId(roomMember.getRoomId());
            List<RoomMember> roomMemberList = roomMemberRepository.findByRoomId(roomMember.getRoomId());
            // 이 부분에서 roomMember와 room의 정보를 원하는 형태로 조합
            result.add(new RoomMemberResponseDto(roomMember, room, roomMemberList));
        }

        return result;
    }

    //채팅방 하나 불러오기
    public ChatRoom findById(String userId, String roomId) {// roomId로 채팅방 찾기
        ChatRoom chatRoom = RoomMemberResponseDto.findByRoomId(roomId).orElse(null);
        List<RoomMember> roomMembers = roomMemberRepository.findByUserId(userId);
        List<RoomMember> roomMemberList = roomMemberRepository.findByRoomId(roomMember.getRoomId());

        // 채팅방 소유자 확인 (가정: ChatRoom에 사용자 ID가 포함되어 있음)
        if (chatRoom != null && chatRoom.getUserId().equals(userId)) {
            return chatRoom;
        } else {
            // 채팅방이 없거나 사용자 ID가 일치하지 않는 경우
            throw new IllegalArgumentException("채팅방을 찾을 수 없습니다.");
        }
    }

    //채팅방 생성
    public ChatRoom createRoom(String userId, String no, String stockName, String price, Integer pricePercent, Double orderCnt ) {
        Integer personPercent = (int)(orderCnt*100);
        ChatRoom chatRoom = ChatRoom.create(no, stockName, price, pricePercent, personPercent);

        Room room  = new Room();
        room.setRoomId(chatRoom.getRoomId());
        room.setNo(chatRoom.getNo());
        room.setStockName(chatRoom.getStockName());
        room.setPrice(chatRoom.getPrice());
        room.setPricePercent(chatRoom.getPricePercent());
        room.setPersonCnt(chatRoom.getPersonCnt());
        room.setTotalPercent(chatRoom.getTotalPercent());
        roomRepository.save(room);

        RoomMember roomMember  = new RoomMember();
        roomMember.setRoomId(chatRoom.getRoomId());
        roomMember.setUserId(userId);
        roomMember.setPricePercent(pricePercent);
        roomMember.setTradingCnt(orderCnt);
        roomMember.setPersonPercent(personPercent);
        roomMemberRepository.save(roomMember);

        chatRooms.put(chatRoom.getRoomId(), chatRoom);
        return chatRoom;
    }
}