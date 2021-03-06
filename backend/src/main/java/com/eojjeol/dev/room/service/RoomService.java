package com.eojjeol.dev.room.service;

import com.eojjeol.dev.auction.repository.AuctionRepository;
import com.eojjeol.dev.entity.Auction;
import com.eojjeol.dev.entity.Room;
import com.eojjeol.dev.entity.member.Member;
import com.eojjeol.dev.member.repository.MemberRepository;
import com.eojjeol.dev.room.dto.RoomDto;
import com.eojjeol.dev.room.repository.RoomQueryRepository;
import com.eojjeol.dev.room.repository.RoomRepository;
import com.eojjeol.dev.security.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final MemberRepository memberRepository;
    private final RoomRepository roomRepository;
    private final AuctionRepository auctionRepository;
    private final RoomQueryRepository roomQueryRepository;

    @Transactional
    public ResponseEntity<RoomDto> createRoom(RoomDto roomDto) {
        try {
            Member member = SecurityUtil.getCurrentEmail().flatMap(memberRepository::findOneWithAuthoritiesByEmail).orElse(null);

            Room isRoomExist = roomQueryRepository.findRoomBySessionId(member.getEmail().replaceAll("[@.]", "-"));

            if(isRoomExist != null) {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }

            Room room = Room.builder()
                    .sessionId(member.getEmail().replaceAll("[@.]", "-"))
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
            Room room = roomQueryRepository.findRoomBySessionId(sessionId);
            if(room == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            roomRepository.deleteById(room.getId());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional
    public ResponseEntity<RoomDto> selectCurrentAuction(Long auctionId) {
        try {
            Auction selectedAuction = auctionRepository.findById(auctionId).orElse(null);

            if(selectedAuction == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            Room room = roomRepository.getById(selectedAuction.getRoom().getId());
            room.setCurrentAuction(selectedAuction.getId());

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<RoomDto> selectRoom(String sessionId) {
        try {
            Room room = roomQueryRepository.findRoomBySessionId(sessionId);
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
