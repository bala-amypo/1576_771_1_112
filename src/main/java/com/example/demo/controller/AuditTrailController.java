package com.example.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.AuditTrailRecord;
import com.example.demo.service.AuditTrailService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/audit")
@RequiredArgsConstructor
public class AuditTrailController {

    private final AuditTrailService auditService;

    @PostMapping
    public ResponseEntity<AuditTrailRecord> log(
            @RequestBody AuditTrailRecord record) {

        return ResponseEntity.status(201)
                .body(auditService.logEvent(record));
    }

    @GetMapping("/credential/{credentialId}")
    public ResponseEntity<List<AuditTrailRecord>> getByCredential(
            @PathVariable Long credentialId) {

        return ResponseEntity.ok(
                auditService.getLogsByCredential(credentialId));
    }
}
