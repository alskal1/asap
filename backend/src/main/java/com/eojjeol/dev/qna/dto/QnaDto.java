package com.eojjeol.dev.qna.dto;

import com.eojjeol.dev.entity.Qna;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QnaDto {

    private Long id;

    private String title;

    private String content;

    public static QnaDto from(Qna qna) {
        if (qna == null) return null;
        return QnaDto.builder()
                .id(qna.getId())
                .title(qna.getTitle())
                .content(qna.getContent())
                .build();
    }
}
