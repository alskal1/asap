package com.eojjeol.dev.member.dto;

import com.eojjeol.dev.authority.dto.AuthorityDto;
import com.eojjeol.dev.entity.member.Member;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;


import javax.persistence.Column;
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
    @ApiModelProperty(example = "bing")
    private String name;

    @NotNull
    @Size(min = 10, max = 20)
    @ApiModelProperty(example = "010-1234-5678")
    private String phone;

    @ApiModelProperty(example = "0")
    private Integer point;

    @ApiModelProperty(hidden = true)
    private Set<AuthorityDto> authorityDtoSet;

    public static MemberDto from(Member member) {
        if(member == null) return null;

        return MemberDto.builder()
                .email(member.getEmail())
                .name(member.getName())
                .phone(member.getPhone())
                .point(member.getPoint())
                .authorityDtoSet(member.getMemberAuthorities().stream()
                        .map(authority -> AuthorityDto.builder().authorityName(authority.getAuthority().getAuthorityName()).build())
                        .collect(Collectors.toSet()))
                .build();
    }
}