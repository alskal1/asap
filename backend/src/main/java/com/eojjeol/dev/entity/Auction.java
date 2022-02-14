package com.eojjeol.dev.entity;

import com.eojjeol.dev.entity.BaseTimeEntity;
import com.eojjeol.dev.entity.History;
import com.eojjeol.dev.entity.Room;
import com.eojjeol.dev.entity.member.Member;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    private Integer bidTerm;

    private Integer priceTerm;

    private Integer currentPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToOne(mappedBy = "auction", cascade = CascadeType.ALL)
    private History history;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;

}
