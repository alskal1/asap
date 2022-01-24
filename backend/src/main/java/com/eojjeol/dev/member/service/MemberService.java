package com.eojjeol.dev.member.service;

import java.util.HashSet;
import java.util.Optional;

import com.eojjeol.dev.authority.entity.Authority;
import com.eojjeol.dev.member.dto.MemberDto;
import com.eojjeol.dev.member.entity.Member;
import com.eojjeol.dev.member.entity.MemberAuthority;
import com.eojjeol.dev.member.repository.MemberRepository;
import com.eojjeol.dev.security.util.SecurityUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

//    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
//        this.userRepository = userRepository;
//        this.passwordEncoder = passwordEncoder;
//    }

    @Transactional
    public MemberDto signup(MemberDto memberDto) throws Exception {
        Member member1 = memberRepository.findOneWithAuthoritiesByUsername(memberDto.getUsername()).orElse(null);
        if (memberRepository.findOneWithAuthoritiesByUsername(memberDto.getUsername()).orElse(null) != null) {
            throw new Exception("이미 가입되어 있는 유저입니다.");
        }

        Authority authority = Authority.builder()
                .authorityName("ROLE_USER")
                .build();

        Member member = Member.builder()
                .username(memberDto.getUsername())
                .password(passwordEncoder.encode(memberDto.getPassword()))
                .nickname(memberDto.getNickname())
                .memberAuthorities(new HashSet<>())
                .build();

        MemberAuthority.createMemberAuthority(member, authority);
        Member saveMember = memberRepository.save(member);
        MemberDto saveMemberDto = MemberDto.from(saveMember);
        return saveMemberDto;
    }

    public MemberDto updateMember(MemberDto memberDto) {
        Member findMember = SecurityUtil.getCurrentUsername().flatMap(memberRepository::findOneWithAuthoritiesByUsername).orElse(null);
        findMember.setNickname(memberDto.getNickname());
        findMember.setPassword(passwordEncoder.encode(memberDto.getPassword()));
        MemberDto updateMemberDto = MemberDto.from(findMember);
        return updateMemberDto;
    }

    public MemberDto deleteMember() {
        MemberDto memberDto = MemberDto.from(SecurityUtil.getCurrentUsername().flatMap(memberRepository::findOneWithAuthoritiesByUsername).orElse(null));
        if (memberDto != null) {
            String username = SecurityUtil.getCurrentUsername().get();
            memberRepository.deleteMemberByUsername(username);
        }
        return memberDto;
    }

    @Transactional(readOnly = true)
    public MemberDto getUserWithAuthorities(String username) {
        return MemberDto.from(memberRepository.findOneWithAuthoritiesByUsername(username).orElse(null));
    }

    @Transactional(readOnly = true)
    public MemberDto getMyUserWithAuthorities() {
        return MemberDto.from(SecurityUtil.getCurrentUsername().flatMap(memberRepository::findOneWithAuthoritiesByUsername).orElse(null));
    }


}