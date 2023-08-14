package com.hackerton.pieSystem.controller;

import com.hackerton.pieSystem.domain.RoomDto;
import com.hackerton.pieSystem.domain.Room;
import com.hackerton.pieSystem.domain.RoomMemberResponseDto;
import com.hackerton.pieSystem.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000", "http://54.180.115.36:3000"})
@RequestMapping("/chat")
public class ChatRoomController {
    private final ChatService chatService;

    // 채팅방 생성
    @PostMapping("/create-room")
    @ResponseBody
    public RoomDto createRoom(@RequestParam String userId
            , @RequestParam String no
            , @RequestParam String stockName
            , @RequestParam String price
            , @RequestParam Integer pricePercent
            , @RequestParam Double orderCnt ) {

        return chatService.createRoom(userId, no, stockName, price, pricePercent, orderCnt);
    }

    // 모든 채팅방 목록 반환
    @GetMapping("/rooms/{userId}")
    @ResponseBody
    public List<RoomMemberResponseDto> findAllRoom(@PathVariable String userId) {

        return chatService.findAllRoom(userId);
    }


    // 특정 채팅방 조회
    @GetMapping("/room/{userId}/{roomMemberId}")
    @ResponseBody
    public RoomMemberResponseDto roomInfo(@PathVariable String userId
                            , @PathVariable String roomMemberId) {
        return chatService.findById(userId, roomMemberId);
    }

    // 채팅방 입장 화면
    @GetMapping("/room/enter/{roomId}")
    public String roomDetail(Model model, @PathVariable String roomId) {
        model.addAttribute("roomId", roomId);
        return "redirect:http://localhost:3000/room";
    }
}