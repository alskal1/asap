package com.eojjeol.dev.entity;

import com.eojjeol.dev.entity.member.Member;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Auction extends BaseTimeEntity {

    @Id @GeneratedValue
    @Column(name = "auction_id")
    private Long id;

    private String productName;

    private Integer finalPrice;

    private Integer startPrice;

    private String origin;

    private Integer currentPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;

}
