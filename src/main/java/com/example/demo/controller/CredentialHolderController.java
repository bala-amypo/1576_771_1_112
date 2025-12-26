package com.example.demo.controller;

import com.example.demo.entity.CredentialHolderProfile;
import com.example.demo.service.CredentialHolderProfileService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/holders")
@RequiredArgsConstructor
public class CredentialHolderController {

    private final CredentialHolderProfileService service;

    @PostMapping
    public ResponseEntity<CredentialHolderProfile> create(
            @RequestBody CredentialHolderProfile profile) {

        return ResponseEntity.ok(service.createHolder(profile));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CredentialHolderProfile> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getHolderById(id));
    }

    @GetMapping
    public ResponseEntity<List<CredentialHolderProfile>> getAll() {
        return ResponseEntity.ok(service.getAllHolders());
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<CredentialHolderProfile> updateStatus(
            @PathVariable Long id,
            @RequestParam boolean active) {

        return ResponseEntity.ok(service.updateHolderStatus(id, active));
    }
}
