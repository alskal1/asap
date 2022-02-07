package com.eojjeol.dev.qna.repository;

import com.eojjeol.dev.entity.QnaComment;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.eojjeol.dev.entity.QQnaComment.qnaComment;

@Repository
public class QnaCmQueryRepository extends QuerydslRepositorySupport {

    private JPAQueryFactory jpaQueryFactory;

    public QnaCmQueryRepository(JPAQueryFactory jpaQueryFactory) {
        super(QnaComment.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    public List<QnaComment> findAllByQnaId(Long qnaId) {
        return jpaQueryFactory.selectFrom(qnaComment)
                .where(qnaComment.qna.id.eq(qnaId))
                .fetch();
    }
}
