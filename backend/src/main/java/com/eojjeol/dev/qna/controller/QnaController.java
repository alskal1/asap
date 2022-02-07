package com.eojjeol.dev.qna.controller;

import com.eojjeol.dev.qna.dto.QnaDto;
import com.eojjeol.dev.qna.service.QnaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/qna")
@RequiredArgsConstructor
public class QnaController {

    private final QnaService qnaService;

    @GetMapping("/")
    public ResponseEntity<List<QnaDto>> selectQna() {
        return qnaService.selectAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<QnaDto> selectOne(@PathVariable Long id){
        return qnaService.selectQna(id);
    }

    @PostMapping("/")
    public ResponseEntity<QnaDto> createQna(@RequestBody QnaDto qnaDto) {
        return qnaService.createQna(qnaDto);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<QnaDto> updateQna(@RequestBody QnaDto qnaDto) {
        return qnaService.updateQna(qnaDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<QnaDto> deleteQna(@PathVariable Long id){
        return qnaService.deleteQna(id);
    }
}
