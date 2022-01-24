package com.eojjeol.dev.entity.auction;

import com.eojjeol.dev.entity.BaseTimeEntity;
import com.eojjeol.dev.entity.BidHistory;
import com.eojjeol.dev.entity.Product;
import com.eojjeol.dev.entity.WinHistory;
import com.eojjeol.dev.entity.member.Member;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Auction extends BaseTimeEntity {

    @Id @GeneratedValue
    @Column(name = "auction_io")
    private Long id;

    private Integer final_price;

    private Integer start_price;

    private Integer bid_term;

    private Integer price_term;

    private Integer current_price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToOne(mappedBy = "auction", cascade = CascadeType.ALL)
    private WinHistory winHistory;

    @OneToMany(mappedBy = "auction", cascade = CascadeType.ALL)
    private List<BidHistory> bidHistoryList = new ArrayList<>();

    @OneToMany(mappedBy = "auction", cascade = CascadeType.ALL)
    private List<Product> productList = new ArrayList<>();

    @OneToMany(mappedBy = "auction", cascade = CascadeType.ALL)
    private List<AuctionViewer> auctionViewerList = new ArrayList<>();
}
