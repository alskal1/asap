package com.eojjeol.dev.history.controller;

import com.eojjeol.dev.history.dto.HistoryDto;
import com.eojjeol.dev.history.service.HistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/history")
@RequiredArgsConstructor

public class HistoryController {

    private final HistoryService historyService;

    @PostMapping("/")
    public ResponseEntity<HistoryDto> createHistory(@RequestBody HistoryDto historyDto) {
        return null;
    }

    /*@GetMapping("/")
    public ResponseEntity<HistoryDto> selectAll() {
        return null;
    }*/

    @GetMapping("/sell-list")
    public ResponseEntity<HistoryDto> selectSell() {
        return null;
    }

    @GetMapping("/win-list")
    public ResponseEntity<HistoryDto> selectWin() {
        return null;
    }
}
