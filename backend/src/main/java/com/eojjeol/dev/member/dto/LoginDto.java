package com.eojjeol.dev.member.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {

    @NotNull
    @Size(min = 3, max = 50)
    @ApiModelProperty(example = "test@test.com")
    private String email;

    @NotNull
    @Size(min = 3, max = 100)
    @ApiModelProperty(example = "test")
    private String password;
}