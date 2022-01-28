package com.eojjeol.dev.entity;

import com.eojjeol.dev.entity.auction.Auction;
import com.eojjeol.dev.entity.member.Member;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class BidHistory {

    @Id @GeneratedValue
    @Column(name = "bid_history_id")
    private Long id;

    private Integer price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "auction_id")
    private Auction auction;
}
