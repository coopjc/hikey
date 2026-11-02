package me.coopjc.hikey.controller;

import me.coopjc.hikey.HikeyApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/")
public class StatusController {

    private static final String name = "hikey-api";
    private static final String version = HikeyApplication.version;
    private static final String status = "healthy";

    @GetMapping()
    public ResponseEntity<Map<String,String>> getDefault() {
        Map<String, String> response = new HashMap<>();

        response.put("name", name);
        response.put("version", version);
        response.put("status", status);
        response.put("timestamp", LocalDateTime.now().toString());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/api")
    public ResponseEntity<Map<String,String>> getStatus() {
        Map<String, String> response = new HashMap<>();

        response.put("name", name);
        response.put("version", version);
        response.put("status", status);
        response.put("timestamp", LocalDateTime.now().toString());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}