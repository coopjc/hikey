package me.coopjc.hikey.service;

import me.coopjc.hikey.dto.hike.CreateHikeRequest;
import me.coopjc.hikey.exception.user.UserNotFoundException;
import me.coopjc.hikey.model.Hike;
import me.coopjc.hikey.model.HikeStatus;
import me.coopjc.hikey.model.User;
import me.coopjc.hikey.repository.HikeRepository;
import me.coopjc.hikey.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class HikeService {

    private final HikeRepository hikeRepository;
    private final UserRepository userRepository;

    public HikeService(HikeRepository hikeRepository, UserRepository userRepository) {
        this.hikeRepository = hikeRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public List<Hike> getHikes(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        return hikeRepository.findByUserId(userId);
    }

    @Transactional
    public Hike createHike(Long userId, CreateHikeRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        Hike hike = new Hike();

        hike.setUser(user);
        hike.setName(request.name());
        hike.setNotes(request.notes());
        hike.setStatus(HikeStatus.CREATED);

        return hikeRepository.save(hike);
    }
}