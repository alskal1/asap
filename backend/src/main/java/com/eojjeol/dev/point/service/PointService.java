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

@Service
@RequiredArgsConstructor
public class PointService {

    private final MemberRepository memberRepository;
    private final PointRepository pointRepository;
    private final PointQueryRepository pointQueryRepository;

    @Transactional
    public ResponseEntity<PointDto> createPoint(PointDto pointDto) {
        try {
            Member member = SecurityUtil.getCurrentEmail().flatMap(memberRepository::findOneWithAuthoritiesByEmail).orElse(null);

            if(pointDto.getStatus().equals(PointStatus.WIN)) {
                member.setPoint(member.getPoint() - pointDto.getPrice());
            } else {
                member.setPoint(member.getPoint() + pointDto.getPrice());
            }

            Point point = Point.builder()
                    .status(pointDto.getStatus())
                    .price(pointDto.getPrice())
                    .member(member)
                    .build();

            Point savePoint = pointRepository.save(point);
            PointDto savePointDto = PointDto.from(savePoint);
            return new ResponseEntity<>(savePointDto, HttpStatus.OK);
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
