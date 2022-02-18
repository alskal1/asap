package com.eojjeol.dev.history.repository;

import com.eojjeol.dev.entity.History;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.eojjeol.dev.entity.QHistory.history;

@Repository
public class HistoryQueryRepository extends QuerydslRepositorySupport {
    private final JPAQueryFactory jpaQueryFactory;

    public HistoryQueryRepository(JPAQueryFactory jpaQueryFactory) {
        super(History.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    public List<History> findAllSell(String sellerId) {
        return jpaQueryFactory.selectFrom(history)
                .where(history.sellerId.eq(sellerId))
                .orderBy(history.createdDate.desc())
                .fetch();
    }

    public List<History> findAllWin(Long id) {
        return jpaQueryFactory.selectFrom(history)
                .where(history.member.id.eq(id))
                .orderBy(history.createdDate.desc())
                .fetch();
    }
}
