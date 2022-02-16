package com.eojjeol.dev.point.dto;

import com.eojjeol.dev.entity.BaseTimeEntity;
import com.eojjeol.dev.entity.Point;
import com.eojjeol.dev.entity.PointStatus;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PointDto extends BaseTimeEntity {

    @ApiModelProperty(hidden = true)
    private Long id;

    private String sellerId;

    private Integer price;

    private LocalDateTime date;


    public static PointDto from(Point point) {
        if(point == null) return null;
        return PointDto.builder()
                .id(point.getId())
                .date(point.getCreatedDate())
                .price(point.getPrice())
                .build();
    }
}
