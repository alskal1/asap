package com.eojjeol.dev.point.service;

import com.eojjeol.dev.entity.Point;
import com.eojjeol.dev.entity.PointStatus;
import com.eojjeol.dev.entity.member.Member;
import com.eojjeol.dev.member.repository.MemberRepository;
import com.eojjeol.dev.point.dto.PointDto;
import com.eojjeol.dev.point.repository.PointRepository;
import com.eojjeol.dev.security.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PointService {

    private final MemberRepository memberRepository;
    private final PointRepository pointRepository;

    public ResponseEntity<PointDto> createPoint(PointDto pointDto) {
        try {
            Member member = SecurityUtil.getCurrentEmail().flatMap(memberRepository::findOneWithAuthoritiesByEmail).orElse(null);
            Point point = Point.builder()
                    .status(pointDto.getStatus())
                    .price(pointDto.getPrice())
                    .member(member)
                    .build();
            Point savePoint = pointRepository.save(point);
            PointDto savePointDto = PointDto.from(savePoint);
            return new ResponseEntity<>(savePointDto, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // id =  point id
    // we want member id
    public ResponseEntity<PointDto> selectPoint(){
        try {
//            PointDto findPoint = PointDto.from(pointRepository.findAll());
//            return new ResponseEntity<PointDto>(findPoint, HttpStatus.OK);
            return null;
        }catch(Exception e){
            return new ResponseEntity<PointDto>(HttpStatus.BAD_REQUEST);
        }
    }

    // where status eq status => queryDsl
    public ResponseEntity<PointDto> selectPointByStatus(PointStatus status) {
        try {
//            Point point = pointRepository.findByStatus(status).orElse(null);
//            PointDto pointDto = PointDto.from(point);
//            return new ResponseEntity<>(pointDto, HttpStatus.OK);
            return null;
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
