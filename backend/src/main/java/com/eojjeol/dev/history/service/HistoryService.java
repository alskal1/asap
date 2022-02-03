package com.eojjeol.dev.history.service;

import com.eojjeol.dev.auction.repository.AuctionRepository;
import com.eojjeol.dev.entity.History;
import com.eojjeol.dev.entity.member.Member;
import com.eojjeol.dev.history.dto.HistoryDto;
import com.eojjeol.dev.history.repository.HistoryQueryRepository;
import com.eojjeol.dev.history.repository.HistoryRepository;
import com.eojjeol.dev.member.repository.MemberRepository;
import com.eojjeol.dev.security.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HistoryService {

    private final MemberRepository memberRepository;
    private final HistoryRepository historyRepository;
    private final AuctionRepository auctionRepository;
    private final HistoryQueryRepository historyCustomRepository;

    @Transactional
    public ResponseEntity<HistoryDto> createHistory(HistoryDto historyDto) {
        try {
            Member member = SecurityUtil.getCurrentEmail().flatMap(memberRepository::findOneWithAuthoritiesByEmail).orElse(null);
            History history = History.builder()
                    .productName(historyDto.getProductName())
                    .count(historyDto.getCount())
                    .finalPrice(historyDto.getFinalPrice())
                    .deliveryState(historyDto.getDeliveryState())
                    .sellerId(historyDto.getSellerId())
                    .member(member)
                    .auction(auctionRepository.getById(historyDto.getAuctionId()))
                    .build();

            History savedHistory = historyRepository.save(history);
            HistoryDto savedHistoryDto = HistoryDto.from(savedHistory);
            return new ResponseEntity<>(savedHistoryDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /*public ResponseEntity<HistoryDto> selectAll() {
        try {

        } catch (Exception e) {

        }

        return null;
    }*/

    public ResponseEntity<List<HistoryDto>> selectSell() {
        try {
            Member member = SecurityUtil.getCurrentEmail().flatMap(memberRepository::findOneWithAuthoritiesByEmail).orElse(null);
            List<History> historyList = historyCustomRepository.findAllSell(member.getEmail());
            List<HistoryDto> historyDtoList = new ArrayList<>();

            for(History history : historyList) {
                historyDtoList.add(HistoryDto.from(history));
            }

            return new ResponseEntity<>(historyDtoList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    public ResponseEntity<List<HistoryDto>> selectWin() {
        try {
            Member member = SecurityUtil.getCurrentEmail().flatMap(memberRepository::findOneWithAuthoritiesByEmail).orElse(null);
            List<History> historyList = historyCustomRepository.findAllWin(member.getId());
            List<HistoryDto> historyDtoList = new ArrayList<>();

            for(History history : historyList) {
                historyDtoList.add(HistoryDto.from(history));
            }

            return new ResponseEntity<>(historyDtoList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
}
