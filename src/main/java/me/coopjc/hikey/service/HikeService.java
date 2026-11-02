package me.coopjc.hikey.service;

import me.coopjc.hikey.dto.hike.CreateHikeRequest;
import me.coopjc.hikey.dto.hike.PushHikePointsRequest;
import me.coopjc.hikey.dto.hike.UpdateHikeRequest;
import me.coopjc.hikey.exception.hike.HikeNotFoundException;
import me.coopjc.hikey.model.*;
import me.coopjc.hikey.repository.HikePointRepository;
import me.coopjc.hikey.repository.HikeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class HikeService {

    private final HikeRepository hikeRepository;
    private final HikePointRepository hikePointRepository;
    private final UserService userService;

    public HikeService(HikeRepository hikeRepository, HikePointRepository hikePointRepository, UserService userService) {
        this.hikeRepository = hikeRepository;
        this.hikePointRepository = hikePointRepository;
        this.userService = userService;
    }

    @Transactional
    public Page<Hike> getHikes(Long userId, Pageable pageable) {
        User user = userService.getUserById(userId);
        return hikeRepository.findByUserId(user.getId(), pageable);
    }

    public HikeStats getStats(Long userId) {
        User user = userService.getUserById(userId);
        return hikeRepository.findStatsByUserId(user.getId());
    }


    @Transactional
    public Hike getHikeById(Long userId, Long hikeId) {
        User user = userService.getUserById(userId);

        Hike hike = hikeRepository.findById(hikeId)
                .orElseThrow(HikeNotFoundException::new);

        // Hike doesn't belong to user
        if(!user.getId().equals(hike.getUser().getId())) {
            throw new HikeNotFoundException();
        }

        return hike;
    }

    @Transactional
    public Hike createHike(Long userId, CreateHikeRequest request) {
        User user = userService.getUserById(userId);

        Hike hike = new Hike();

        hike.setUser(user);
        hike.setName(request.name());
        hike.setNotes(request.notes());
        hike.setStatus(HikeStatus.CREATED);

        return hikeRepository.save(hike);
    }

    @Transactional
    public Hike updateHike(Long userId, Long hikeId, UpdateHikeRequest request) {
        User user = userService.getUserById(userId);

        Hike hike = hikeRepository.findById(hikeId)
                .orElseThrow(HikeNotFoundException::new);

        // Hike doesn't belong to user
        if(!user.getId().equals(hike.getUser().getId())) {
            throw new HikeNotFoundException();
        }

        if(request.name() != null) {
            hike.setName(request.name());
        }

        if(request.notes() != null) {
            hike.setNotes(request.notes());
        }

        if(request.status() != null) {
            hike.setStatus(request.status());
        }

        if(request.distanceMiles() != null) {
            hike.setDistanceMiles(request.distanceMiles());
        }

        if(request.durationMin() != null) {
            hike.setDurationMin(request.durationMin());
        }

        return hikeRepository.save(hike);
    }

    @Transactional
    public void deleteHike(Long userId, Long hikeId) {
        User user = userService.getUserById(userId);

        Hike hike = hikeRepository.findById(hikeId)
                .orElseThrow(HikeNotFoundException::new);

        // Hike doesn't belong to user
        if(!user.getId().equals(hike.getUser().getId())) {
            throw new HikeNotFoundException();
        }

        hikeRepository.delete(hike);
    }

    // Hike Tracking

    @Transactional
    public List<HikePoint> getHikePointsByHikeId(Long userId, Long hikeId) {
        User user = userService.getUserById(userId);

        Hike hike = hikeRepository.findById(hikeId)
                .orElseThrow(HikeNotFoundException::new);

        // Hike doesn't belong to user
        if(!user.getId().equals(hike.getUser().getId())) {
            throw new HikeNotFoundException();
        }

        return hikePointRepository.findByHikeIdOrderByIdAsc(hikeId);
    }

    @Transactional
    public void pushHikePoints(Long userId, Long hikeId, PushHikePointsRequest request) {
        User user = userService.getUserById(userId);

        Hike hike = hikeRepository.findById(hikeId)
                .orElseThrow(HikeNotFoundException::new);

        // Hike doesn't belong to user
        if(!user.getId().equals(hike.getUser().getId())) {
            throw new HikeNotFoundException();
        }

        List<HikePoint> points = request.hikePoints().stream()
                .map(p -> {
                    HikePoint point = new HikePoint();

                    point.setHike(hike);
                    point.setLatitude(p.latitude());
                    point.setLongitude(p.longitude());
                    point.setElevation(p.elevation());
                    point.setSavedAt(p.savedAt());

                    return point;
                })
                .toList();

        hikePointRepository.saveAll(points);
    }
}