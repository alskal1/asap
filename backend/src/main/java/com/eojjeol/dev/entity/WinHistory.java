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

    private String productName;

    private Integer count;

    private Integer finalPrice;

    private WinState winState;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "auction_id")
    private Auction auction;

}
