package com.eojjeol.dev.member.service;

import java.util.HashSet;

import com.eojjeol.dev.entity.Authority;
import com.eojjeol.dev.member.dto.MemberDto;
import com.eojjeol.dev.entity.member.Member;
import com.eojjeol.dev.entity.member.MemberAuthority;
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
        Member member1 = memberRepository.findOneWithAuthoritiesByEmail(memberDto.getUsername()).orElse(null);
        if (memberRepository.findOneWithAuthoritiesByEmail(memberDto.getUsername()).orElse(null) != null) {
            throw new Exception("이미 가입되어 있는 유저입니다.");
        }

        Authority authority = Authority.builder()
                .authorityName("ROLE_USER")
                .build();

        Member member = Member.builder()
                .name(memberDto.getUsername())
                .password(passwordEncoder.encode(memberDto.getPassword()))
                .memberAuthorities(new HashSet<>())
                .build();

        MemberAuthority.createMemberAuthority(member, authority);
        Member saveMember = memberRepository.save(member);
        MemberDto saveMemberDto = MemberDto.from(saveMember);
        return saveMemberDto;
    }

    public MemberDto updateMember(MemberDto memberDto) {
        Member findMember = SecurityUtil.getCurrentUsername().flatMap(memberRepository::findOneWithAuthoritiesByEmail).orElse(null);
        findMember.setName(memberDto.getNickname());
        findMember.setPassword(passwordEncoder.encode(memberDto.getPassword()));
        MemberDto updateMemberDto = MemberDto.from(findMember);
        return updateMemberDto;
    }

    public MemberDto deleteMember() {
        MemberDto memberDto = MemberDto.from(SecurityUtil.getCurrentUsername().flatMap(memberRepository::findOneWithAuthoritiesByEmail).orElse(null));
        if (memberDto != null) {
            String username = SecurityUtil.getCurrentUsername().get();
            memberRepository.deleteMemberByEmail(username);
        }
        return memberDto;
    }

    @Transactional(readOnly = true)
    public MemberDto getUserWithAuthorities(String email) {
        return MemberDto.from(memberRepository.findOneWithAuthoritiesByEmail(email).orElse(null));
    }

    @Transactional(readOnly = true)
    public MemberDto getMyUserWithAuthorities() {
        return MemberDto.from(SecurityUtil.getCurrentUsername().flatMap(memberRepository::findOneWithAuthoritiesByEmail).orElse(null));
    }


}