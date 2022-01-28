package com.eojjeol.dev.entity.member;

import com.eojjeol.dev.entity.Authority;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
public class MemberAuthority {
    @Id @GeneratedValue
    @Column(name = "member_authority_id")
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "authority_name")
    Authority authority;

    public void setMember(Member member) {
        this.member = member;
        Set<MemberAuthority> memberAuthorities = member.getMemberAuthorities();
        memberAuthorities.add(this);
    }

    public static MemberAuthority createMemberAuthority(Member member, Authority authority) {
        MemberAuthority memberAuthority = new MemberAuthority();
        memberAuthority.setMember(member);
        memberAuthority.setAuthority(authority);
        return memberAuthority;
    }
}
