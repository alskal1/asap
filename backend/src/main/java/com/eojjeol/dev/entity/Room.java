package com.eojjeol.dev.entity;

import com.eojjeol.dev.entity.auction.Auction;
import com.eojjeol.dev.entity.member.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Room extends BaseTimeEntity{

    @Id @GeneratedValue
    @Column(name = "room_id")
    private Long id;

    private String sessionId;

    private String token;

    private String title;

    private String description;

    @OneToMany(mappedBy = "room", cascade=CascadeType.ALL)
    private List<Auction> auctionList = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public void addAuction(Auction auction) {
        this.auctionList.add(auction);
    }
}
