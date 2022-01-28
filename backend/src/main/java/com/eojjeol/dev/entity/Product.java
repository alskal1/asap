package com.eojjeol.dev.entity;

import com.eojjeol.dev.entity.auction.Auction;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Product {
    @Id @GeneratedValue
    @Column(name = "product_id")
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "auction_id")
    private Auction auction;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<WinHistory> winHistoryList = new ArrayList<>();
}
