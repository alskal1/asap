package com.eojjeol.dev.entity;

import com.eojjeol.dev.entity.member.Member;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class ChargeHistory {

    @Id @GeneratedValue
    @Column(name = "charge_history_id")
    private Long id;

    private Integer money;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
}
