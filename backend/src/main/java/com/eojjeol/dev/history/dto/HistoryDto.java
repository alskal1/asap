package com.eojjeol.dev.history.dto;

import com.eojjeol.dev.entity.BaseTimeEntity;
import com.eojjeol.dev.entity.History;
import com.eojjeol.dev.entity.DeliveryState;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HistoryDto extends BaseTimeEntity {

    @ApiModelProperty(hidden = true)
    private Long id;

    private String productName;

    private Integer finalPrice;

    @ApiModelProperty(example = "")
    private DeliveryState deliveryState;

    private String sellerId;

    private LocalDateTime date;

    public static HistoryDto from(History history) {
        if(history == null) return null;
        return HistoryDto.builder()
                .id(history.getId())
                .date(history.getCreatedDate())
                .productName(history.getProductName())
                .finalPrice(history.getFinalPrice())
                .deliveryState(history.getDeliveryState())
                .sellerId(history.getSellerId())
                .build();
    }
}
