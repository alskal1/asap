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

    @PatchMapping("/")
    public ResponseEntity<NoticeDto> updateNotice(@RequestBody NoticeDto noticeDto) {
        return noticeService.updateNotice(noticeDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<NoticeDto> deleteNotice(@PathVariable Long id) {
        return noticeService.deleteNotice(id);
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<List<NoticeDto>> selectByTitle(@PathVariable String title){
        return noticeService.selectNoticeByTitle(title);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<List<NoticeDto>> selectByEmail(@PathVariable String email) {
        return noticeService.selectNoticeByEmail(email);
    }
}
