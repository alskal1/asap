package com.eojjeol.dev.point.service;

import com.eojjeol.dev.entity.Point;
import com.eojjeol.dev.entity.PointStatus;
import com.eojjeol.dev.entity.member.Member;
import com.eojjeol.dev.member.repository.MemberRepository;
import com.eojjeol.dev.point.dto.PointDto;
import com.eojjeol.dev.point.repository.PointQueryRepository;
import com.eojjeol.dev.point.repository.PointRepository;
import com.eojjeol.dev.security.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.eojjeol.dev.entity.member.QMember.member;

@Service
@RequiredArgsConstructor
public class PointService {

    private final MemberRepository memberRepository;
    private final PointRepository pointRepository;
    private final PointQueryRepository pointQueryRepository;

    @Transactional
    public ResponseEntity<PointDto> createPoint(PointDto pointDto) {
        try {
            Member buyer = SecurityUtil.getCurrentEmail().flatMap(memberRepository::findOneWithAuthoritiesByEmail).orElse(null);
            Member seller = memberRepository.findOneWithAuthoritiesByEmail(pointDto.getSellerId()).orElse(null);

            Point buyerPoint = Point.builder()
                    .status(PointStatus.PAYMENT)
                    .price(pointDto.getPrice())
                    .member(buyer)
                    .build();

            Point sellerPoint = Point.builder()
                    .status(PointStatus.SELL)
                    .price(pointDto.getPrice())
                    .member(seller)
                    .build();

            Point saveBuyerPoint = pointRepository.save(buyerPoint);
            Point saveSellerPoint = pointRepository.save(sellerPoint);

            buyer.setPoint(buyer.getPoint() - saveBuyerPoint.getPrice());
            seller.setPoint(seller.getPoint() + saveSellerPoint.getPrice());

            return new ResponseEntity<>(HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional
    public ResponseEntity<PointDto> chargePoint(PointDto pointDto) {
        try {
            Member member = SecurityUtil.getCurrentEmail().flatMap(memberRepository::findOneWithAuthoritiesByEmail).orElse(null);

            Point point = Point.builder()
                    .status(PointStatus.CHARGE)
                    .price(pointDto.getPrice())
                    .member(member)
                    .build();

            Point savePoint = pointRepository.save(point);
            member.setPoint(member.getPoint() + savePoint.getPrice());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    public ResponseEntity<List<PointDto>> selectPoint(){
        try {
            Member member = SecurityUtil.getCurrentEmail().flatMap(memberRepository::findOneWithAuthoritiesByEmail).orElse(null);
            List<Point> pointList = pointQueryRepository.findAllPoints(member.getId());
            List<PointDto> pointDtoList = new ArrayList<>();

            for(Point point : pointList) {
                pointDtoList.add(PointDto.from(point));
            }

            return new ResponseEntity<>(pointDtoList, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<List<PointDto>> selectPointByStatus(PointStatus status) {
        try {
            Member member = SecurityUtil.getCurrentEmail().flatMap(memberRepository::findOneWithAuthoritiesByEmail).orElse(null);
            List<Point> pointList = pointQueryRepository.findAllPointsByStatus(member.getId(), status);
            List<PointDto> pointDtoList = new ArrayList<>();

            for(Point point : pointList) {
                pointDtoList.add(PointDto.from(point));
            }

            return new ResponseEntity<>(pointDtoList, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
