package com.eojjeol.dev.qna.service;

import com.eojjeol.dev.entity.Qna;
import com.eojjeol.dev.entity.member.Member;
import com.eojjeol.dev.member.repository.MemberRepository;
import com.eojjeol.dev.qna.dto.QnaDto;
import com.eojjeol.dev.qna.repository.QnaRepository;
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
public class QnaService {

    private final QnaRepository qnaRepository;
    private final MemberRepository memberRepository;

    public ResponseEntity<List<QnaDto>> selectAll() {
        try{
            List<Qna> qnaList = qnaRepository.findAll();
            List<QnaDto> qnaDtoList = new ArrayList<>();
            for(Qna qna : qnaList) {
                qnaDtoList.add(QnaDto.from(qna));
            }
            return new ResponseEntity<>(qnaDtoList, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<QnaDto> selectQna(Long id) {
        try{
            Qna qna = qnaRepository.findById(id).orElse(null);
            QnaDto qnaDto = QnaDto.from(qna);
            return new ResponseEntity<>(qnaDto, HttpStatus.OK);
        }catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional
    public ResponseEntity<QnaDto> createQna(QnaDto qnaDto) {
        try{
            Member member = SecurityUtil.getCurrentEmail().flatMap(memberRepository::findOneWithAuthoritiesByEmail).orElse(null);
            Qna qna = Qna.builder()
                    .title(qnaDto.getTitle())
                    .content((qnaDto.getContent()))
                    .member(member)
                    .build();
            Qna saveQna = qnaRepository.save(qna);
            QnaDto saveQnaDto = QnaDto.from(saveQna);
            return new ResponseEntity<>(saveQnaDto, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional
    public ResponseEntity<QnaDto> updateQna(QnaDto qnaDto) {
        try{
            Qna qna= qnaRepository.findById(qnaDto.getId()).orElse(null);
            qna.setTitle(qnaDto.getTitle());
            qna.setContent(qnaDto.getContent());
            QnaDto updateQnaDto = QnaDto.from(qna);
            return new ResponseEntity<>(updateQnaDto,HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional
    public ResponseEntity<QnaDto> deleteQna(Long id){
        try{
            Qna qna = qnaRepository.findById(id).orElse(null);
            qnaRepository.deleteById(qna.getId());
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
