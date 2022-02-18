package com.eojjeol.dev.auction.repository;

import com.eojjeol.dev.entity.Auction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AuctionRepository extends JpaRepository<Auction, Long> {

    void deleteById(Long id);
    Optional<Auction> findById(Long id);
    List<Auction> findAll();
}
