package com.eojjeol.dev.auction.service;

import com.eojjeol.dev.auction.dto.AuctionDto;
import com.eojjeol.dev.auction.repository.AuctionRepository;
import com.eojjeol.dev.entity.auction.Auction;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@AllArgsConstructor
@Transactional
public class AuctionService {

    private final AuctionRepository auctionRepository;

    public ResponseEntity<AuctionDto> createAuction(AuctionDto auctionDto) {
        Auction auction = Auction.builder()
                .productName(auctionDto.getProductName())
                .finalPrice(auctionDto.getFinalPrice())
                .startPrice(auctionDto.getStartPrice())
                .bidTerm(auctionDto.getBidTerm())
                .priceTerm(auctionDto.getPriceTerm())
                .currentPrice(auctionDto.getCurrentPrice())
                .build();


        Auction saveAuction = auctionRepository.save(auction);
        AuctionDto saveAuctionDto = AuctionDto.from(saveAuction);
        return new ResponseEntity<AuctionDto>(saveAuctionDto, HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<AuctionDto> selectOneAuction(Long id) {
        try {
            AuctionDto findAuction = AuctionDto.from(auctionRepository.findById(id).orElse(null));
            return new ResponseEntity<AuctionDto>(findAuction, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<AuctionDto>(HttpStatus.BAD_REQUEST);
        }
    }

//    public ResponseEntity<AuctionDto> updateAuction(AuctionDto auctionDto) {
//
//    }

    public ResponseEntity<AuctionDto> deleteAuction(Long id) {
        try{
            AuctionDto deleteAuction = AuctionDto.from(auctionRepository.findById(id).orElse(null));
            System.out.println("deleteAuction = " + deleteAuction);
            auctionRepository.deleteById(deleteAuction.getId());
            return new ResponseEntity<AuctionDto>(HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<AuctionDto>(HttpStatus.BAD_REQUEST);
        }
    }

}
