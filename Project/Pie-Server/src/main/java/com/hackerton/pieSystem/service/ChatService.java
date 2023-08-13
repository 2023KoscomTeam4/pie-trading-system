package com.hackerton.pieSystem.service;

import com.hackerton.pieSystem.domain.ChatRoom;
import com.hackerton.pieSystem.domain.Room;
import com.hackerton.pieSystem.domain.RoomMember;
import com.hackerton.pieSystem.domain.RoomMemberResponseDto;
import com.hackerton.pieSystem.repository.KospiStockRepository;
import com.hackerton.pieSystem.repository.RoomMemberRepository;
import com.hackerton.pieSystem.repository.RoomRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.internal.StringUtil;
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

    //채팅방 생성
    @Transactional
    public ChatRoom createRoom(String userId, String no, String stockName, String price, Integer pricePercent, Double orderCnt ) {
        Integer personPercent = (int)(orderCnt*100);

        List<Room> roomList = null;
        Room room = null;
        roomList = roomRepository.findRoomByNoAndPriceAndPricePercent(no, price, pricePercent);
        ChatRoom chatRoom = new ChatRoom();
        if(roomList == null || StringUtil.isBlank(roomList.get(0).getRoomId())) {
            //같은 no(주식번호), price(가격), pricePercent(가격범위)가 없는 경우,
            chatRoom = ChatRoom.create(no, stockName, price, pricePercent, personPercent);
            room = new Room();
            room.setRoomId(chatRoom.getRoomId());
            room.setNo(chatRoom.getNo());
            room.setStockName(chatRoom.getStockName());
            room.setPrice(chatRoom.getPrice());
            room.setPricePercent(chatRoom.getPricePercent());
            room.setPersonCnt(chatRoom.getPersonCnt());
            room.setTotalPercent(chatRoom.getTotalPercent());
            roomRepository.save(room);

        }else{
            //해당 방이 있는 경우
            room = roomList.get(0);
            room.setPersonCnt(room.getPersonCnt()+1);
            room.setTotalPercent(room.getTotalPercent()+personPercent);
            roomRepository.save(room);

            chatRoom.setRoomId(room.getRoomId());
            chatRoom.setNo(room.getNo());
            chatRoom.setStockName(room.getStockName());
            chatRoom.setPrice(room.getPrice());
            chatRoom.setPricePercent(room.getPricePercent());
            chatRoom.setPersonCnt(room.getPersonCnt());
            chatRoom.setTotalPercent(room.getTotalPercent());
        }
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
    public RoomMemberResponseDto findById(String userId, String roomMemberId) {
//        //roomId로 채팅방 찾기
//        return chatRooms.get(roomId);

        RoomMemberResponseDto result = new RoomMemberResponseDto();
        RoomMember selectedRoom = roomMemberRepository.findByUserIdAndId(userId, roomMemberId);
        Room room = roomRepository.findByRoomId(selectedRoom.getRoomId());
        List<RoomMember> roomMemberList = roomMemberRepository.findByRoomId(selectedRoom.getRoomId());
        result = new RoomMemberResponseDto(selectedRoom, room, roomMemberList);

        // 채팅방 소유자 확인 (가정: ChatRoom에 사용자 ID가 포함되어 있음)
        if (selectedRoom != null && selectedRoom.getUserId().equals(userId)) {
            return result;
        } else {
            // 채팅방이 없거나 사용자 ID가 일치하지 않는 경우
            throw new IllegalArgumentException("채팅방을 찾을 수 없습니다.");
        }
    }

}