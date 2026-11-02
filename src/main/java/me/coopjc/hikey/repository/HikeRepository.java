package me.coopjc.hikey.repository;

import me.coopjc.hikey.model.Hike;
import me.coopjc.hikey.model.HikeStats;
import me.coopjc.hikey.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HikeRepository extends JpaRepository<Hike, Long> {
    Page<Hike> findByUserId(long userId, Pageable pageable);
    Optional<Hike> findByIdAndUserId(long hikeId, long userId);

    @Query("""
    SELECT COUNT(h) AS totalHikes,
           COALESCE(SUM(h.distanceMiles), 0) AS totalDistance
    FROM Hike h
    WHERE h.user.id = :userId
      AND h.status = "COMPLETED"
    """)
    HikeStats findStatsByUserId(@Param("userId") long userId);
}