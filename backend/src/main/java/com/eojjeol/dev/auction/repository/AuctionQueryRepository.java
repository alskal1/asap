package com.eojjeol.dev.auction.repository;

import com.eojjeol.dev.entity.Auction;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.eojjeol.dev.entity.QAuction.auction;

@Repository
public class AuctionQueryRepository extends QuerydslRepositorySupport {

    private JPAQueryFactory jpaQueryFactory;

    public AuctionQueryRepository(JPAQueryFactory jpaQueryFactory) {
        super(Auction.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    public List<Auction> findAllAuctionsByRoomId(Long id) {
        return jpaQueryFactory.selectFrom(auction)
                .where(auction.room.id.eq(id))
                .fetch();
    }

    public void deleteAllAuctionByRoomId(Long id) {
        jpaQueryFactory.delete(auction)
                .where(auction.room.id.eq(id))
                .notifyAll();
    }
}
