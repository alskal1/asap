package com.eojjeol.dev.entity;

import com.eojjeol.dev.entity.member.Member;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Notice {
    @Id @GeneratedValue
    @Column(name = "notice_id")
    private Long id;

    private String title;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
}
