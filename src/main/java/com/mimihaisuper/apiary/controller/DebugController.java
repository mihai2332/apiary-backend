package com.mimihaisuper.apiary.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/debug")
public class DebugController {

    @GetMapping({"/", ""})
    public ResponseEntity getPing() {
        return ResponseEntity.ok("pong");
    }

    @GetMapping("/pong")
    public ResponseEntity getDoublePing() {
        return ResponseEntity.ok("double pong");
    }
}
