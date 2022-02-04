package com.eojjeol.dev.notice.controller;

import com.eojjeol.dev.notice.dto.NoticeDto;
import com.eojjeol.dev.notice.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notice")
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;

    @GetMapping("/")
    public ResponseEntity<List<NoticeDto>> selectNotice() {
        return noticeService.selectAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<NoticeDto> selectOne(@PathVariable Long id) {
        return noticeService.selectNotice(id);
    }

    @PostMapping("/")
    public ResponseEntity<NoticeDto> createNotice(@RequestBody NoticeDto noticeDto) {
        return noticeService.createNotice(noticeDto);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<NoticeDto> updateNotice(@PathVariable Long id, @RequestBody NoticeDto noticeDto) {
        return noticeService.updateNotice(id, noticeDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<NoticeDto> deleteNotice(@PathVariable Long id) {
        return noticeService.deleteNotice(id);
    }
}
