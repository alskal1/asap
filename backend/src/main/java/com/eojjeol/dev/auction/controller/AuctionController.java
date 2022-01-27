package com.eojjeol.dev.auction.controller;

import com.eojjeol.dev.auction.dto.AuctionDto;
import com.eojjeol.dev.auction.service.AuctionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auction")
@AllArgsConstructor
public class AuctionController {

    private final AuctionService auctionService;

    @PostMapping
    public ResponseEntity<AuctionDto> createAuction(@RequestBody AuctionDto auctionDto) {
        return auctionService.createAuction(auctionDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuctionDto> selectAuction(@PathVariable Long id) {
        return auctionService.selectOneAuction(id);
    }

//    @PatchMapping
//    public ResponseEntity<AuctionDto> updateAuction(@RequestBody AuctionDto auctionDto) {
//        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AuctionDto> deleteAuction(@PathVariable Long id) {
        return auctionService.deleteAuction(id);
    }
}

