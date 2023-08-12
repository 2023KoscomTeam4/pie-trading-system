package com.hackerton.pieSystem.controller;

import com.hackerton.pieSystem.domain.ChatRoom;
import com.hackerton.pieSystem.domain.RoomMemberResponseDto;
import com.hackerton.pieSystem.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/chat")
public class ChatRoomController {
    private final ChatService chatService;

    // 채팅방 생성
    @PostMapping("/create-room")
    @ResponseBody
    public ChatRoom createRoom(@RequestParam String userId
            ,@RequestParam String no
            ,@RequestParam String stockName
            ,@RequestParam String price
            ,@RequestParam Integer pricePercent
            ,@RequestParam Double orderCnt ) {

        return chatService.createRoom(userId, no, stockName, price, pricePercent, orderCnt);
    }

    // 채팅방 입장 화면
    @GetMapping("/room/enter/{roomId}")
    public String roomDetail(Model model, @PathVariable String roomId) {
        model.addAttribute("roomId", roomId);
        return "redirect:http://localhost:3000/room";
    }

    // 모든 채팅방 목록 반환
    @GetMapping("/rooms")
    @ResponseBody
    public List<RoomMemberResponseDto> room(@RequestParam String userId) {

        return chatService.findAllRoom(userId);
    }

    // 특정 채팅방 조회
    @GetMapping("/room/{roomId}")
    @ResponseBody
    public ChatRoom roomInfo(@PathVariable String roomId) {

        return chatService.findById(roomId);
    }
}