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

    @Transactional
    public MemberDto signup(MemberDto memberDto) throws Exception {
        Member existMember = memberRepository.findOneWithAuthoritiesByEmail(memberDto.getEmail()).orElse(null);
        if (existMember != null) {
            throw new Exception("이미 가입되어 있는 유저입니다.");
        }


        Authority authority = Authority.builder()
                .authorityName("ROLE_USER")
                .build();

        Member member = Member.builder()
                .email(memberDto.getEmail())
                .password(passwordEncoder.encode(memberDto.getPassword()))
                .name(memberDto.getName())
                .point(0)
                .phone(memberDto.getPhone())
                .address(memberDto.getAddress())
                .memberAuthorities(new HashSet<>())
                .build();

        MemberAuthority.createMemberAuthority(member, authority);
        Member saveMember = memberRepository.save(member);
        MemberDto saveMemberDto = MemberDto.from(saveMember);
        return saveMemberDto;
    }

    public MemberDto updateMember(MemberDto memberDto) {
        try{
            Member findMember = SecurityUtil.getCurrentEmail().flatMap(memberRepository::findOneWithAuthoritiesByEmail).orElse(null);
            findMember.setName(memberDto.getName());
            findMember.setPassword(passwordEncoder.encode(memberDto.getPassword()));
            findMember.setPhone(memberDto.getPhone());
            findMember.setAddress(memberDto.getAddress());
            MemberDto updateMemberDto = MemberDto.from(findMember);
            return updateMemberDto;
        }catch(Exception e){
            return null;
        }

    }

    public MemberDto deleteMember() {
        MemberDto memberDto = MemberDto.from(SecurityUtil.getCurrentEmail().flatMap(memberRepository::findOneWithAuthoritiesByEmail).orElse(null));
        if (memberDto != null) {
            String username = SecurityUtil.getCurrentEmail().get();
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
        return MemberDto.from(SecurityUtil.getCurrentEmail().flatMap(memberRepository::findOneWithAuthoritiesByEmail).orElse(null));
    }


}