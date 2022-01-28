package com.eojjeol.dev.entity;

import com.eojjeol.dev.entity.auction.Auction;
import com.eojjeol.dev.entity.member.Member;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Room {

    @Id @GeneratedValue
    @Column(name = "room_id")
    private Long id;

    private String url;

    @OneToMany(mappedBy = "room", cascade=CascadeType.ALL)
    private List<Auction> auctionList = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

}
