package com.eojjeol.dev.auction.dto;

import com.eojjeol.dev.entity.Auction;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuctionDto {

    @ApiModelProperty(hidden = true)
    private Long id;

    private Long roomId;

    private String productName;

    private Integer finalPrice;

    private Integer startPrice;

    private Integer currentPrice;

    private String origin;

    public static AuctionDto from(Auction auction) {
        if(auction == null) return null;
        return AuctionDto.builder()
                .id(auction.getId())
                .roomId(auction.getRoom().getId())
                .productName(auction.getProductName())
                .finalPrice(auction.getFinalPrice())
                .startPrice(auction.getStartPrice())
                .currentPrice(auction.getCurrentPrice())
                .origin(auction.getOrigin())
                .build();
    }
}
