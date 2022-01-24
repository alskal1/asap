package com.eojjeol.dev.authority.entity;

import com.eojjeol.dev.member.entity.MemberAuthority;
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

//    @Id
//    @GeneratedValue
//    @Column(name = "authority_id")
//    Long id;

    @Id
    @Column(name = "authority_name", length = 50)
    private String authorityName;

    @OneToMany(mappedBy = "authority", cascade = CascadeType.ALL)
    private Set<MemberAuthority> memberAuthorities;
}
