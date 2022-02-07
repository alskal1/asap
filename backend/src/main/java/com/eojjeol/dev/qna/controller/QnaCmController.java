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

    // 하나의 댓글 조회
    @GetMapping("/{id}")
    public ResponseEntity<QnaCmDto> selectComment(@PathVariable Long id) {
        return qnaCmService.selectOne(id);
    }

    // 한 개의 Qna에 달린 여러 개의 Comments 조회
    @GetMapping("/list/{qnaId}")
    public ResponseEntity<List<QnaCmDto>> selectComments(@PathVariable Long qnaId) {
        return qnaCmService.selectComments(qnaId);
    }

    @PostMapping("/")
    public ResponseEntity<QnaCmDto> createComment(@RequestBody QnaCmDto qnaCmDto) {
        return qnaCmService.createComment(qnaCmDto);
    }

    @PatchMapping("/")
    public ResponseEntity<QnaCmDto> updateComment(@RequestBody QnaCmDto qnaCmDto) {
        return qnaCmService.updateQna(qnaCmDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<QnaCmDto> deleteComment(@PathVariable Long id) {
        return qnaCmService.deleteQna(id);
    }
}
