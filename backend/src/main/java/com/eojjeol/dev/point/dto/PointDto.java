package com.eojjeol.dev.point.dto;

import com.eojjeol.dev.entity.Point;
import com.eojjeol.dev.entity.PointStatus;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PointDto {

    @ApiModelProperty(hidden = true)
    private Long id;

    private PointStatus status;

    private Integer price;

    public static PointDto from(Point point) {
        if(point == null) return null;
        return PointDto.builder()
                .id(point.getId())
                .status(point.getStatus())
                .price(point.getPrice())
                .build();
    }
}
