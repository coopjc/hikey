package me.coopjc.hikey.repository;

import me.coopjc.hikey.model.Hike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HikeRepository extends JpaRepository<Hike, Long> {
    List<Hike> findByUserId(long userId);
}