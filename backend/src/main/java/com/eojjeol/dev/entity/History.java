package com.eojjeol.dev.entity;

import com.eojjeol.dev.entity.member.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class History extends BaseTimeEntity{
    @Id @GeneratedValue
    @Column(name = "history_id")
    private Long id;

    private String productName;

    private Integer finalPrice;

    private DeliveryState deliveryState;

    private String sellerId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
}
