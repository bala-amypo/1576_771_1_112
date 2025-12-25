package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.CredentialHolderProfile;
import com.example.demo.service.CredentialHolderProfileService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/holder")
@RequiredArgsConstructor
public class CredentialHolderController {

    private final CredentialHolderProfileService service;

    @PostMapping
    public ResponseEntity<CredentialHolderProfile> create(
            @RequestBody CredentialHolderProfile profile) {

        return ResponseEntity.status(201)
                .body(service.createHolder(profile));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CredentialHolderProfile> getById(
            @PathVariable Long id) {

        return ResponseEntity.ok(service.getHolderById(id));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<CredentialHolderProfile> updateStatus(
            @PathVariable Long id,
            @RequestParam boolean active) {

        return ResponseEntity.ok(
                service.updateStatus(id, active));
    }
}
