package com.eojjeol.dev.auction.service;

import com.eojjeol.dev.auction.dto.AuctionDto;
import com.eojjeol.dev.auction.repository.AuctionQueryRepository;
import com.eojjeol.dev.auction.repository.AuctionRepository;
import com.eojjeol.dev.entity.auction.Auction;
import com.eojjeol.dev.room.repository.RoomRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
@Transactional
public class AuctionService {

    private final AuctionRepository auctionRepository;
    private final AuctionQueryRepository auctionQueryRepository;
    private final RoomRepository roomRepository;

    public ResponseEntity<AuctionDto> createAuction(AuctionDto auctionDto) {
        Auction auction = Auction.builder()
                .productName(auctionDto.getProductName())
                .room(roomRepository.getById(auctionDto.getRoomId()))
                .finalPrice(auctionDto.getFinalPrice())
                .startPrice(auctionDto.getStartPrice())
                .bidTerm(auctionDto.getBidTerm())
                .priceTerm(auctionDto.getPriceTerm())
                .currentPrice(auctionDto.getCurrentPrice())
                .build();

        Auction saveAuction = auctionRepository.save(auction);
        AuctionDto saveAuctionDto = AuctionDto.from(saveAuction);
        return new ResponseEntity<>(saveAuctionDto, HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<AuctionDto> selectOneAuction(Long id) {
        try {
            AuctionDto findAuction = AuctionDto.from(auctionRepository.findById(id).orElse(null));
            return new ResponseEntity<>(findAuction, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<AuctionDto> deleteAuction(Long id) {
        try{
            AuctionDto deleteAuction = AuctionDto.from(auctionRepository.findById(id).orElse(null));
            auctionRepository.deleteById(deleteAuction.getId());
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<List<AuctionDto>> selectAllAuction(Long id) {
        try {
            List<Auction> auctionList = auctionQueryRepository.findAllAuctionsByRoomId(id);

            if(auctionList.size() == 0) return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            List<AuctionDto> auctionDtoList = new ArrayList<>();

            for(Auction auction : auctionList) {
                auctionDtoList.add(AuctionDto.from(auction));
            }

            return new ResponseEntity<>(auctionDtoList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
