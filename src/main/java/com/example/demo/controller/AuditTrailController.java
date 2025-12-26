package com.example.demo.controller;

import com.example.demo.entity.AuditTrailRecord;
import com.example.demo.service.AuditTrailService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/audit")
@RequiredArgsConstructor
public class AuditTrailController {

    private final AuditTrailService service;

    // ✅ ADDED
    @PostMapping
    public ResponseEntity<AuditTrailRecord> log(@RequestBody AuditTrailRecord record) {
        return ResponseEntity.ok(service.logEvent(record));
    }

    @GetMapping("/credential/{credentialId}")
    public ResponseEntity<List<AuditTrailRecord>> getByCredential(
            @PathVariable Long credentialId) {
        return ResponseEntity.ok(service.getLogsByCredential(credentialId));
    }

    // ✅ ADDED
    @GetMapping
    public ResponseEntity<List<AuditTrailRecord>> getAll() {
        return ResponseEntity.ok(service.getAllLogs());
    }
}

