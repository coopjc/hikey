package me.coopjc.hikey.controller;

import jakarta.validation.Valid;
import me.coopjc.hikey.dto.hike.CreateHikeRequest;
import me.coopjc.hikey.dto.hike.CreateHikeResponse;
import me.coopjc.hikey.dto.Response;
import me.coopjc.hikey.dto.hike.GetHikesResponse;
import me.coopjc.hikey.dto.hike.HikeResponse;
import me.coopjc.hikey.model.Hike;
import me.coopjc.hikey.service.HikeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hikes")
public class HikeController {

    private final HikeService hikeService;

    public HikeController(HikeService hikeService) {
        this.hikeService = hikeService;
    }

    @GetMapping
    public ResponseEntity<Response> getMyHikes(@AuthenticationPrincipal long userId) {
        List<Hike> hikes = hikeService.getHikes(userId);

        List<HikeResponse> hikeResponses = hikes.stream()
                .map(HikeResponse::from)
                .toList();

        Response response = new Response();

        response.setMessage("Successfully returned hikes.");
        response.setData(GetHikesResponse.from(hikeResponses));
        response.setStatus(HttpStatus.OK.value());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Response> createHike(@AuthenticationPrincipal long userId,
                                               @Valid @RequestBody CreateHikeRequest request
    ) {
        Hike hike = hikeService.createHike(userId, request);

        Response response = new Response();

        response.setMessage("Successfully created hike.");
        response.setData(CreateHikeResponse.from(hike));
        response.setStatus(HttpStatus.CREATED.value());

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}