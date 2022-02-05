package com.eojjeol.dev.qna.repository;

import com.eojjeol.dev.entity.QnaComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface QnaCmRepository extends JpaRepository<QnaComment, Long> {

    @Override
    Optional<QnaComment> findById(Long aLong);
}
