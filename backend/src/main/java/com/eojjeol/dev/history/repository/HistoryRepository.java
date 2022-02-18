package com.eojjeol.dev.history.repository;

import com.eojjeol.dev.entity.History;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryRepository extends JpaRepository<History, Long> {

}
