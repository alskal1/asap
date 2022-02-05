package com.eojjeol.dev.notice.service;


import com.eojjeol.dev.entity.Notice;
import com.eojjeol.dev.entity.member.Member;
import com.eojjeol.dev.member.repository.MemberRepository;
import com.eojjeol.dev.notice.dto.NoticeDto;
import com.eojjeol.dev.notice.repository.NoticeQueryRepository;
import com.eojjeol.dev.notice.repository.NoticeRepository;
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
public class NoticeService {

    private final NoticeRepository noticeRepository;
    private final MemberRepository memberRepository;
    private final NoticeQueryRepository noticeQueryRepository;

    public ResponseEntity<List<NoticeDto>> selectAll() {
        try{
            List<Notice> noticeList = noticeRepository.findAll();
            List<NoticeDto> noticeDtoList = new ArrayList<>();
            for(Notice notice : noticeList) {
                noticeDtoList.add(NoticeDto.from(notice));
            }
            return new ResponseEntity<>(noticeDtoList, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<NoticeDto> selectNotice(Long id) {
        try{
            Notice notice = noticeRepository.findById(id).orElse(null);
            NoticeDto noticeDto = NoticeDto.from(notice);
            return new ResponseEntity<>(noticeDto, HttpStatus.OK);
        }catch(Exception e){
            if(e instanceof NullPointerException) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional
    public ResponseEntity<NoticeDto> createNotice(NoticeDto noticeDto) {
        try{
            Member member = SecurityUtil.getCurrentEmail().flatMap(memberRepository::findOneWithAuthoritiesByEmail).orElse(null);
            Notice notice = Notice.builder()
                    .title(noticeDto.getTitle())
                    .content(noticeDto.getContent())
                    .member(member)
                    .build();
            Notice saveNotice = noticeRepository.save(notice);
            NoticeDto saveNoticeDto = NoticeDto.from(saveNotice);
            return new ResponseEntity<>(saveNoticeDto, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional
    public ResponseEntity<NoticeDto> updateNotice(NoticeDto noticeDto) {
        try{
            Notice notice = noticeRepository.findById(noticeDto.getId()).orElse(null);
            notice.setTitle(noticeDto.getTitle());
            notice.setContent(noticeDto.getContent());
            NoticeDto updateNoticeDto = NoticeDto.from(notice);
            return new ResponseEntity<>(updateNoticeDto, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional
    public ResponseEntity<NoticeDto> deleteNotice(Long id){
        try{
            Notice notice = noticeRepository.findById(id).orElse(null);
            noticeRepository.deleteById(notice.getId());
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<List<NoticeDto>> selectNoticeByTitle(String title) {
        try{
            List<Notice> noticeList = noticeQueryRepository.findByTitle(title);
            List<NoticeDto> noticeDtoList = new ArrayList<>();
            for (Notice notice : noticeList) {
                noticeDtoList.add(NoticeDto.from(notice));
            }
            return new ResponseEntity<List<NoticeDto>>(noticeDtoList, HttpStatus.OK);
        }catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<List<NoticeDto>> selectNoticeByEmail(String email) {
        try{
            List<Notice> noticeList = noticeQueryRepository.findByEmail(email);
            List<NoticeDto> noticeDtoList = new ArrayList<>();
            for(Notice notice : noticeList) {
                noticeDtoList.add(NoticeDto.from(notice));
            }
            return new ResponseEntity<>(noticeDtoList, HttpStatus.OK);
        }catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
