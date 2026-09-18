package me.coopjc.hikey.controller;

import jakarta.validation.Valid;
import me.coopjc.hikey.dto.hike.*;
import me.coopjc.hikey.dto.Response;
import me.coopjc.hikey.model.Hike;
import me.coopjc.hikey.model.HikePoint;
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
    public ResponseEntity<Response> getHikes(@AuthenticationPrincipal long userId) {
        List<Hike> hikes = hikeService.getHikes(userId);

        List<HikeResponse> hikeResponses = hikes.stream()
                .map(HikeResponse::from)
                .toList();

        Response response = new Response();

        response.setMessage("Successfully returned " + hikes.size() + " hikes.");
        response.setData(GetHikesResponse.from(hikeResponses));
        response.setStatus(HttpStatus.OK.value());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getHikeById(@AuthenticationPrincipal long userId,
                                                @PathVariable long id)
    {
        Hike hike = hikeService.getHikeById(userId, id);

        Response response = new Response();

        response.setMessage("Successfully returned hike.");
        response.setData(HikeResponse.from(hike));
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
        response.setData(HikeResponse.from(hike));
        response.setStatus(HttpStatus.CREATED.value());

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Response> updateHike(@AuthenticationPrincipal long userId,
                                               @PathVariable long id,
                                               @Valid @RequestBody UpdateHikeRequest request)
    {
        Hike hike = hikeService.updateHike(userId, id, request);

        Response response = new Response();

        response.setMessage("Successfully updated hike.");
        response.setData(HikeResponse.from(hike));
        response.setStatus(HttpStatus.OK.value());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteHike(@AuthenticationPrincipal long userId,
                                               @PathVariable long id)
    {
        hikeService.deleteHike(userId, id);

        Response response = new Response();

        response.setMessage("Successfully deleted hike.");
        response.setData(null);
        response.setStatus(HttpStatus.OK.value());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Hike Tracking

    @GetMapping("/{id}/points")
    public ResponseEntity<Response> getHikePointsByHikeId(@AuthenticationPrincipal long userId,
                                                          @PathVariable long id)
    {
        List<HikePoint> hikePoints = hikeService.getHikePointsByHikeId(userId, id);

        List<HikePointResponse> hikePointsResponse = hikePoints.stream()
                .map(HikePointResponse::from)
                .toList();

        Response response = new Response();

        response.setMessage("Successfully returned " + hikePoints.size() + " hikes points.");
        response.setData(GetHikePointsResponse.from(hikePointsResponse));
        response.setStatus(HttpStatus.OK.value());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/{id}/points")
    public ResponseEntity<Response> pushHikePoints(@AuthenticationPrincipal long userId,
                                                   @PathVariable long id,
                                                   @Valid @RequestBody PushHikePointsRequest request)
    {
        hikeService.pushHikePoints(userId, id, request);

        Response response = new Response();

        response.setMessage("Successfully pushed hike points");
        response.setData(null);
        response.setStatus(HttpStatus.OK.value());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}