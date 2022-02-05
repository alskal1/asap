package com.eojjeol.dev.qna.controller;

import com.eojjeol.dev.qna.dto.QnaCmDto;
import com.eojjeol.dev.qna.service.QnaCmService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comment")
@RequiredArgsConstructor
public class QnaCmController {

    private final QnaCmService qnaCmService;

    @GetMapping("/{id}")
    public ResponseEntity<List<QnaCmDto>> selectComment(@PathVariable Long id) {
        return null;
    }

    @PostMapping("/")
    public ResponseEntity<QnaCmDto> createComment(@RequestBody QnaCmDto qnaCmDto) {
        return qnaCmService.createComment(qnaCmDto);
    }

    @PatchMapping("/")
    public ResponseEntity<QnaCmDto> updateComment(@RequestBody QnaCmDto qnaCmDto) {
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<QnaCmDto> deleteComment(@PathVariable Long id) {
        return null;
    }
}
