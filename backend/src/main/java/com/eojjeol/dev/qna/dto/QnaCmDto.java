package com.eojjeol.dev.qna.dto;

import com.eojjeol.dev.entity.QnaComment;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QnaCmDto {

    private Long id;

    private String content;

    private Long qnaId;

    public static QnaCmDto from(QnaComment qnaComment) {
        if(qnaComment == null) return null;
        return QnaCmDto.builder()
                .id(qnaComment.getId())
                .content(qnaComment.getContent())
                .qnaId(qnaComment.getQna().getId())
                .build();
    }
}
