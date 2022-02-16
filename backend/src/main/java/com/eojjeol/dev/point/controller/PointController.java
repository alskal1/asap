package com.eojjeol.dev.point.controller;

import com.eojjeol.dev.entity.PointStatus;
import com.eojjeol.dev.point.dto.PointDto;
import com.eojjeol.dev.point.service.PointService;
import com.eojjeol.dev.security.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/point")
@RequiredArgsConstructor
public class PointController {

    private final PointService pointService;

    @PostMapping("/")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<PointDto> createPoint(@RequestBody PointDto pointDto) {
        return pointService.createPoint(pointDto);
    }

    @PostMapping("/charge")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<PointDto> chargePoint(@RequestBody PointDto pointDto) {
        return pointService.chargePoint(pointDto);
    }

    @GetMapping("/")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<List<PointDto>> selectPoint() {
        return pointService.selectPoint();
    }

    @GetMapping("/{status}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<List<PointDto>> selectPointByStatus(@PathVariable PointStatus status){
        return pointService.selectPointByStatus(status);
    }

}
