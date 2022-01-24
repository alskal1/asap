package com.eojjeol.dev.entity.member;

import com.eojjeol.dev.entity.*;
import com.eojjeol.dev.entity.auction.Auction;
import com.eojjeol.dev.entity.auction.AuctionViewer;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Member {

    @Id
    @Column(name = "member_id")
    @GeneratedValue
    private Long id;

    @Column(length = 50, unique = true)
    private String email;

    @Column(length = 100)
    private String password;

    @Column(length = 50)
    private String phone;

    @Column(length = 50)
    private String name;

    private Integer point;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<WinHistory> winHistoryList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<BidHistory> bidHistoryList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Auction> auctionList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private Set<MemberAuthority> memberAuthorities = new HashSet<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<AuctionViewer> auctionViewerList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<ChargeHistory> chargeHistoryList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Notice> noticeList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Qna> qnaList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<QnaComment> qnaCommentList = new ArrayList<>();
}
