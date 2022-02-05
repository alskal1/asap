package com.eojjeol.dev.qna.service;

import com.eojjeol.dev.entity.Qna;
import com.eojjeol.dev.entity.QnaComment;
import com.eojjeol.dev.entity.member.Member;
import com.eojjeol.dev.member.repository.MemberRepository;
import com.eojjeol.dev.qna.dto.QnaCmDto;
import com.eojjeol.dev.qna.repository.QnaCmQueryRepository;
import com.eojjeol.dev.qna.repository.QnaCmRepository;
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
public class QnaCmService {

    private final QnaCmRepository qnaCmRepository;
    private final QnaRepository qnaRepository;
    private final QnaCmQueryRepository qnaCmQueryRepository;
    private final MemberRepository memberRepository;

//    public ResponseEntity<List<QnaCmDto>> selectOne(Long qnaId) {
//        try{
//            List<QnaComment> qnaCommentList = qnaCmQueryRepository.findByQnaId(qnaId);
//            List<QnaCmDto> qnaCmDtoList = new ArrayList<>();
//            for (QnaComment qnaComment : qnaCommentList) {
//                qnaCmDtoList.add
//            }
//        }catch(Exception e){
//
//        }
//    }

    @Transactional
    public ResponseEntity<QnaCmDto> createComment(QnaCmDto qnaCmDto){
        try{
            Member member = SecurityUtil.getCurrentEmail().flatMap(memberRepository::findOneWithAuthoritiesByEmail).orElse(null);
            Qna qna = qnaRepository.findById(qnaCmDto.getQnaId()).orElse(null);
            QnaComment qnaComment = QnaComment.builder()
                    .content(qnaCmDto.getContent())
                    .qna(qna)
                    .member(member)
                    .build();
            QnaComment saveComment = qnaCmRepository.save(qnaComment);
            QnaCmDto saveQnaCmDto = QnaCmDto.from(saveComment);
            return new ResponseEntity<>(saveQnaCmDto, HttpStatus.OK);
        }catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }
}
