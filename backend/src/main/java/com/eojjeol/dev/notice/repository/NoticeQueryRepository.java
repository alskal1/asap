package com.eojjeol.dev.notice.repository;

import com.eojjeol.dev.entity.Notice;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.eojjeol.dev.entity.QNotice.notice;

@Repository
public class NoticeQueryRepository extends QuerydslRepositorySupport {

    private JPAQueryFactory jpaQueryFactory;

    public NoticeQueryRepository(JPAQueryFactory jpaQueryFactory) {
        super(Notice.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    public List<Notice> findByTitle(String title) {
        return jpaQueryFactory.selectFrom(notice)
                .where(notice.title.contains(title))
                .fetch();
    }

    public List<Notice> findByEmail(String email) {
        return jpaQueryFactory.selectFrom(notice)
                .where(notice.member.email.contains(email))
                .fetch();
    }
}
