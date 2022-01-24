package com.eojjeol.dev.member.dto;

import com.eojjeol.dev.authority.dto.AuthorityDto;
import com.eojjeol.dev.entity.member.Member;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberDto {

    @NotNull
    @Size(min = 3, max = 50)
    @ApiModelProperty(example = "test@test.com")
    private String email;

    @ApiModelProperty(example = "test")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull
    @Size(min = 3, max = 100)
    private String password;

    @NotNull
    @Size(min = 3, max = 50)
    private String nickname;

    @ApiModelProperty(hidden = true)
    private Set<AuthorityDto> authorityDtoSet;

    public static MemberDto from(Member member) {
        if(member == null) return null;

        return MemberDto.builder()
                .email(member.getEmail())
                .nickname(member.getName())
                .authorityDtoSet(member.getMemberAuthorities().stream()
                        .map(authority -> AuthorityDto.builder().authorityName(authority.getAuthority().getAuthorityName()).build())
                        .collect(Collectors.toSet()))
                .build();
    }
}