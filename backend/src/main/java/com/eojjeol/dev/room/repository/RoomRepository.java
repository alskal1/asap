package com.eojjeol.dev.room.repository;

import com.eojjeol.dev.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Long> {
    @Override
    List<Room> findAll();

    @Override
    Optional<Room> findById(Long id);

    @Override
    void deleteById(Long id);

    Optional<Room> findBySessionId(String sessionId);
}
