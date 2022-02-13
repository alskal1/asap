package com.eojjeol.dev.point.repository;

import com.eojjeol.dev.entity.Point;
import com.eojjeol.dev.entity.PointStatus;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.eojjeol.dev.entity.QPoint.point;

@Repository
public class PointQueryRepository extends QuerydslRepositorySupport {
    private JPAQueryFactory jpaQueryFactory;

    public PointQueryRepository(JPAQueryFactory jpaQueryFactory) {
        super(Point.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    public List<Point> findAllPoints(Long id) {
        return jpaQueryFactory.selectFrom(point)
                .where(point.member.id.eq(id))
                .orderBy(point.createdDate.desc())
                .fetch();
    }

    public List<Point> findAllPointsByStatus(Long id, PointStatus pointStatus) {
        return jpaQueryFactory.selectFrom(point)
                .where(point.member.id.eq(id),
                        point.status.eq(pointStatus))
                .fetch();
    }
}
