package com.eojjeol.dev.point.controller;

import com.eojjeol.dev.entity.PointStatus;
import com.eojjeol.dev.point.dto.PointDto;
import com.eojjeol.dev.point.service.PointService;
import com.eojjeol.dev.security.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/point")
@RequiredArgsConstructor
public class PointController {

    private final PointService pointService;

    @PostMapping("/")
    public ResponseEntity<PointDto> createPoint(@RequestBody PointDto pointDto) {
        return pointService.createPoint(pointDto);
    }

    @GetMapping
    public ResponseEntity<PointDto> selectPoint() {
        return null;
    }

    @GetMapping("/{status}")
    public ResponseEntity<PointDto> selectPointByStatus(@PathVariable PointStatus status){
        return null;
    }

}
