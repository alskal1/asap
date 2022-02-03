package com.eojjeol.dev.point.repository;

import com.eojjeol.dev.entity.Point;
import com.eojjeol.dev.entity.PointStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PointRepository extends JpaRepository<Point,Long> {

}
