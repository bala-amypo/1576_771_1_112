package com.example.demo.controller;

import com.example.demo.entity.CredentialHolderProfile;
import com.example.demo.service.CredentialHolderProfileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/holders")
@Tag(name = "Credential Holders", description = "Credential holder management endpoints")
public class CredentialHolderController {
    
    private final CredentialHolderProfileService holderService;
    
    public CredentialHolderController(CredentialHolderProfileService holderService) {
        this.holderService = holderService;
    }
    
    @PostMapping
    @Operation(summary = "Create credential holder")
    public ResponseEntity<CredentialHolderProfile> create(@RequestBody CredentialHolderProfile profile) {
        return ResponseEntity.ok(holderService.createHolder(profile));
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Get holder by ID")
    public ResponseEntity<CredentialHolderProfile> getById(@PathVariable Long id) {
        return ResponseEntity.ok(holderService.getHolderById(id));
    }
    
    @GetMapping
    @Operation(summary = "List all holders")
    public ResponseEntity<List<CredentialHolderProfile>> getAll() {
        return ResponseEntity.ok(holderService.getAllHolders());
    }
    
    @PutMapping("/{id}/status")
    @Operation(summary = "Update active status")
    public ResponseEntity<CredentialHolderProfile> updateStatus(@PathVariable Long id, @RequestParam boolean active) {
        return ResponseEntity.ok(holderService.updateHolderStatus(id, active));
    }
}