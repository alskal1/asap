package com.eojjeol.dev.member.controller;

import com.eojjeol.dev.jwt.JwtFilter;
import com.eojjeol.dev.jwt.TokenDto;
import com.eojjeol.dev.jwt.TokenProvider;
import com.eojjeol.dev.member.dto.LoginDto;
import com.eojjeol.dev.member.dto.MemberDto;
import com.eojjeol.dev.member.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/member")
@AllArgsConstructor
public class MemberController {
    private final TokenProvider tokenProvider;
    private final MemberService memberService;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    @PostMapping("/signup")
    public ResponseEntity<MemberDto> signup(@Valid @RequestBody MemberDto memberDto) throws Exception {
        return ResponseEntity.ok(memberService.signup(memberDto));
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDto> authorize(@Valid @RequestBody LoginDto loginDto) {

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());

        // authenticate 함수가 실행 될때 CustomUserDetailsService 의 loadUserByUsername 실행된다
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.createToken(authentication);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);

        return new ResponseEntity<>(new TokenDto(jwt), httpHeaders, HttpStatus.OK);
    }
    @PatchMapping("/")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<?> updateMember(@RequestBody MemberDto memberDto) {
        return new ResponseEntity<MemberDto>(memberService.updateMember(memberDto),HttpStatus.OK);
    }

    @DeleteMapping("/")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<?> deleteMember(HttpServletRequest request) {
        return new ResponseEntity<MemberDto>(memberService.deleteMember(),HttpStatus.OK);
    }

    @GetMapping("/user")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<MemberDto> getMyUserInfo(HttpServletRequest request) {
        return ResponseEntity.ok(memberService.getMyUserWithAuthorities());
    }
//
    @GetMapping("/accreditation/{username}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<MemberDto> getUserInfo(@PathVariable String username) {
        return ResponseEntity.ok(memberService.getUserWithAuthorities(username));
    }
}
