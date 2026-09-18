package me.coopjc.hikey.repository;

import me.coopjc.hikey.model.HikePoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HikePointRepository extends JpaRepository<HikePoint, Long> {
    List<HikePoint> findByHikeIdOrderByIdAsc(long hikeId);
}