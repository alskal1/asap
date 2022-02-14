package com.eojjeol.dev.entity;

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

    private String title;

    private String description;

    @OneToMany(mappedBy = "room", cascade=CascadeType.ALL)
    private List<Auction> auctionList = new ArrayList<>();

    public void addAuction(Auction auction) {
        this.auctionList.add(auction);
    }
}
