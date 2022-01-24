package com.eojjeol.dev.entity;

import com.eojjeol.dev.entity.auction.Auction;
import com.eojjeol.dev.entity.member.Member;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class WinHistory {
    @Id @GeneratedValue
    @Column(name = "win_history_id")
    private Long id;

    private Float weight;

    private Integer count;

    private Integer final_price;

    private WinState winState;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "auction_id")
    private Auction auction;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;
}
