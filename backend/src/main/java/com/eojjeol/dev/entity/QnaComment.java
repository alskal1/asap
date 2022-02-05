package com.eojjeol.dev.entity;

import com.eojjeol.dev.entity.member.Member;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QnaComment {

    @Id @GeneratedValue
    @Column(name = "qna_comment_id")
    private Long id;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "qna_id")
    private Qna qna;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
}
