package com.eojjeol.dev.entity;

import com.eojjeol.dev.entity.member.MemberAuthority;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "authority")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Authority {

    @Id
    @Column(name = "authority_name", length = 50)
    private String authorityName;

    @OneToMany(mappedBy = "authority")
    private Set<MemberAuthority> memberAuthorities;
}
