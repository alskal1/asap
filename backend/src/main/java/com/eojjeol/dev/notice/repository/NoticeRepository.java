package com.eojjeol.dev.notice.repository;

import com.eojjeol.dev.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NoticeRepository extends JpaRepository<Notice, Long> {

    @Override
    List<Notice> findAll();

    @Override
    Optional<Notice> findById(Long id);

    @Override
    void deleteById(Long id);
}
