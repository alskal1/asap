package com.eojjeol.dev.member.repository;

import com.eojjeol.dev.member.entity.Member;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    // Lazy 조회가인 Eager 조회로 authorities 정보를 같이 가지고 온다.
//    @EntityGraph(attributePaths = "memberAuthorities")
//    Optional<Member> findOneWithAuthoritiesByUsername(String username);

    @EntityGraph(attributePaths = "memberAuthorities")
    Optional<Member> findOneWithAuthoritiesByUsername(String username);

    void deleteMemberByUsername(String username);

}
