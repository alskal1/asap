package com.eojjeol.dev.room.service;

import com.eojjeol.dev.entity.Room;
import com.eojjeol.dev.entity.member.Member;
import com.eojjeol.dev.member.repository.MemberRepository;
import com.eojjeol.dev.room.dto.RoomDto;
import com.eojjeol.dev.room.repository.RoomRepository;
import com.eojjeol.dev.security.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final MemberRepository memberRepository;
    private final RoomRepository roomRepository;

    @Transactional
    public ResponseEntity<RoomDto> createRoom(RoomDto roomDto) {
        try {
            Member member = SecurityUtil.getCurrentEmail().flatMap(memberRepository::findOneWithAuthoritiesByEmail).orElse(null);

            Room isRoomExist = roomRepository.findBySessionId(member.getEmail()).orElse(null);

            if(isRoomExist != null) {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }

            Room room = Room.builder()
                    .sessionId(member.getEmail())
                    .title(roomDto.getTitle())
                    .description(roomDto.getDescription())
                    .build();

            Room savedRoom = roomRepository.save(room);
            RoomDto saveRoomDto = RoomDto.from(savedRoom);
            return new ResponseEntity<>(saveRoomDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional
    public ResponseEntity<RoomDto> deleteRoom(String sessionId) {
        try {
            Room room = roomRepository.findBySessionId(sessionId).orElse(null);
            roomRepository.deleteById(room.getId());

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<RoomDto> selectRoom(String sessionId) {
        try {
            Room room = roomRepository.findBySessionId(sessionId).orElse(null);
            RoomDto roomDto = RoomDto.from(room);

            return new ResponseEntity<>(roomDto, HttpStatus.OK);
        } catch (Exception e) {
            if(e instanceof NullPointerException) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<List<RoomDto>> selectAll() {
        try {
            List<Room> roomList = roomRepository.findAll();
            List<RoomDto> roomDtoList = new ArrayList<>();
            for (Room room : roomList) {
                roomDtoList.add(RoomDto.from(room));
            }

            return new ResponseEntity<>(roomDtoList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
