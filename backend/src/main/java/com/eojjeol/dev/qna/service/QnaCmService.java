package com.eojjeol.dev.qna.service;

import com.eojjeol.dev.entity.Qna;
import com.eojjeol.dev.entity.QnaComment;
import com.eojjeol.dev.entity.member.Member;
import com.eojjeol.dev.member.repository.MemberRepository;
import com.eojjeol.dev.qna.dto.QnaCmDto;
import com.eojjeol.dev.qna.dto.QnaDto;
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
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QnaCmService {

    private final MemberRepository memberRepository;
    private final QnaRepository qnaRepository;
    private final QnaCmRepository qnaCmRepository;
    private final QnaCmQueryRepository qnaCmQueryRepository;

    public ResponseEntity<List<QnaCmDto>> selectComments(Long qnaId) {
        try {
            List<QnaComment> qnaCommentList = qnaCmQueryRepository.findAllByQnaId(qnaId);
            List<QnaCmDto> qnaCmDtoList = new ArrayList<>();

            for (QnaComment qnaComment : qnaCommentList) {
                qnaCmDtoList.add(QnaCmDto.from(qnaComment));
            }

            return new ResponseEntity<>(qnaCmDtoList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

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

    public ResponseEntity<QnaCmDto> selectOne(Long id) {
        try {
            QnaComment qnaComment = qnaCmRepository.findById(id).orElse(null);

            // 해당 글이 존재하지 않으면 NO_CONTENT STATUS 반환
            if(qnaComment == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            QnaCmDto qnaCmDto = QnaCmDto.from(qnaComment);
            return new ResponseEntity<>(qnaCmDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional
    public ResponseEntity<QnaCmDto> updateQna(QnaCmDto qnaCmDto) {
        try {
            QnaComment qnaComment = qnaCmRepository.findById(qnaCmDto.getId()).orElse(null);

            // 해당 글이 존재하지 않으면 NO_CONTENT STATUS 반환
            if(qnaComment == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            System.out.println("qnaComment = " + qnaComment.getContent());
            qnaComment.setContent(qnaCmDto.getContent());

            QnaCmDto updatedQnaCmDto = QnaCmDto.from(qnaComment);
            return new ResponseEntity<>(updatedQnaCmDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional
    public ResponseEntity<QnaCmDto> deleteQna(Long id) {
        try {
            QnaComment qnaComment = qnaCmRepository.findById(id).orElse(null);

            // 해당 글이 존재하지 않으면 NO_CONTENT STATUS 반환
            if(qnaComment == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            qnaCmRepository.deleteById(qnaComment.getId());

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
