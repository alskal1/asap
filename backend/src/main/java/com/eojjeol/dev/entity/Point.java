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
public class Point extends BaseTimeEntity{

    @Id @GeneratedValue
    @Column(name = "point_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private PointStatus status;

    private Integer price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

}
