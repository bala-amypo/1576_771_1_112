package com.example.demo.controller;

import com.example.demo.entity.CredentialRecord;
import com.example.demo.service.CredentialRecordService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/credentials")
@RequiredArgsConstructor
public class CredentialRecordController {

    private final CredentialRecordService service;

    @PostMapping
    public ResponseEntity<CredentialRecord> create(
            @RequestBody CredentialRecord record) {
        return ResponseEntity.ok(service.createCredential(record));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CredentialRecord> update(
            @PathVariable Long id,
            @RequestBody CredentialRecord update) {
        return ResponseEntity.ok(service.updateCredential(id, update));
    }

    @GetMapping("/holder/{holderId}")
    public ResponseEntity<List<CredentialRecord>> getByHolder(
            @PathVariable Long holderId) {
        return ResponseEntity.ok(service.getCredentialsByHolder(holderId));
    }

    // ✅ FIXED NAME
    @GetMapping("/code/{code}")
    public ResponseEntity<CredentialRecord> getByCode(
            @PathVariable String code) {
        return ResponseEntity.ok(service.getCredentialByCode(code));
    }

    @GetMapping
    public ResponseEntity<List<CredentialRecord>> getAll() {
        return ResponseEntity.ok(service.getAllCredentials());
    }
}
