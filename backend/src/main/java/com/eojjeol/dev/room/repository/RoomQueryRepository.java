package com.eojjeol.dev.room.repository;

import com.eojjeol.dev.entity.Room;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.eojjeol.dev.entity.QRoom.room;

@Repository
public class RoomQueryRepository extends QuerydslRepositorySupport {

    private JPAQueryFactory jpaQueryFactory;

    public RoomQueryRepository(JPAQueryFactory jpaQueryFactory) {
        super(Room.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    public Room findRoomBySessionId(String sessionId) {
        return jpaQueryFactory.selectFrom(room)
                .where(room.sessionId.eq(sessionId))
                .fetchOne();
    }
}
