package com.eojjeol.dev.auction.controller;

import com.eojjeol.dev.auction.dto.AuctionDto;
import com.eojjeol.dev.auction.service.AuctionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/list/{sessionId}")
    public ResponseEntity<List<AuctionDto>> selectAllAuctions(@PathVariable String sessionId) {
        return auctionService.selectAllAuction(sessionId);
    }

    @GetMapping("/current/{sessionId}")
    public ResponseEntity<AuctionDto> selectCurrentAuction(@PathVariable String sessionId) {
        return auctionService.selectCurrentAuction(sessionId);
    }

    @PutMapping("/")
    public ResponseEntity<AuctionDto> updateAuctionPrice(@RequestBody AuctionDto auctionDto) {
        return auctionService.updateAuctionPrice(auctionDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AuctionDto> deleteAuction(@PathVariable Long id) {
        return auctionService.deleteAuction(id);
    }

    @DeleteMapping("/list/{sessionId}")
    public ResponseEntity<AuctionDto> deleteAllAuction(@PathVariable String sessionId) {
        return auctionService.deleteAllAuction(sessionId);
    }
}

