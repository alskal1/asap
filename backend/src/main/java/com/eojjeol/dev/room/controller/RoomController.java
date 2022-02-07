package com.eojjeol.dev.room.controller;

import com.eojjeol.dev.room.dto.RoomDto;
import com.eojjeol.dev.room.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/room")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @PostMapping("/")
//    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<RoomDto> createRoom(@RequestBody RoomDto roomDto) {
        return roomService.createRoom(roomDto);
    }

    @DeleteMapping("/{id}")
//    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<RoomDto> deleteRoom(@PathVariable Long id) {
        return roomService.deleteRoom(id);
    }

    @GetMapping("/{id}")
//    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<RoomDto> selectOne(@PathVariable Long id) {
        return roomService.selectRoom(id);
    }

    @GetMapping("/")
//    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<List<RoomDto>> selectList() {
        return roomService.selectAll();
    }
}
