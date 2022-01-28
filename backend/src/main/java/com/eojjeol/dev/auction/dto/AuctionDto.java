package com.eojjeol.dev.auction.dto;

import com.eojjeol.dev.entity.auction.Auction;
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

    private String productName;

    private Integer finalPrice;

    private Integer startPrice;

    private Integer bidTerm;

    private Integer priceTerm;

    private Integer currentPrice;

    public static AuctionDto from(Auction auction) {
        if(auction == null) return null;
        return AuctionDto.builder()
                .id(auction.getId())
                .productName(auction.getProductName())
                .finalPrice(auction.getFinalPrice())
                .startPrice(auction.getStartPrice())
                .bidTerm(auction.getBidTerm())
                .priceTerm(auction.getPriceTerm())
                .currentPrice(auction.getCurrentPrice())
                .build();
    }

}
