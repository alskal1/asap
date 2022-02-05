package com.eojjeol.dev.qna.repository;

import com.eojjeol.dev.entity.Qna;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface QnaRepository extends JpaRepository<Qna, Long> {

    @Override
    List<Qna> findAll();

    @Override
    Optional<Qna> findById(Long aLong);

    @Override
    void deleteById(Long id);
}
