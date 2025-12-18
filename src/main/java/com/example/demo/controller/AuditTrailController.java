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
public class AuditTrailRecordController {

    private final AuditTrailService auditService;

    @PostMapping
    public ResponseEntity<AuditTrailRecord> log(@RequestBody AuditTrailRecord record){
        return ResponseEntity.status(201).body(auditService.logEvent(record));
    }

    @GetMapping("/credential/{credentialId}")
    public ResponseEntity<List<AuditTrailRecord>> getAuditTrailByCredentialId(
            @PathVariable Long credentialId) {

        List<AuditTrailRecord> logs = auditService.getLogsByCredential(credentialId);

        if(logs.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(logs);
    }

    @GetMapping
    public ResponseEntity<List<AuditTrailRecord>> getAll(){
        List<AuditTrailRecord> logs = auditService.getAllLogs();

        if(logs.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(logs);
    }
}
