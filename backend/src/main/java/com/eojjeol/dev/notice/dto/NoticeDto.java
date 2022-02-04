package com.eojjeol.dev.notice.dto;

import com.eojjeol.dev.entity.Notice;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NoticeDto {

    private Long id;

    private String title;

    private String content;

    public static NoticeDto from(Notice notice) {
        if(notice == null) return null;
        return NoticeDto.builder()
                .id(notice.getId())
                .title(notice.getTitle())
                .content(notice.getContent())
                .build();
    }
}
