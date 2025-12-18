package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.AuditTrailRecord;
import com.example.demo.service.AuditTrailService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/audit")
public class AuditTrailController {

    @Autowired
    AuditTrailService auditTrailService;

    @PostMapping
    public ResponseEntity<AuditTrailRecord> logAuditEvent(@Valid @RequestBody AuditTrailRecord record) {
        AuditTrailRecord loggedRecord = auditTrailService.logEvent(record);
        return ResponseEntity.status(201).body(loggedRecord);
    }

    @GetMapping("/credential/{credentialId}")
    public ResponseEntity<AuditTrailRecord> getAuditTrailByCredentialId(@PathVariable Long credentialId) {
        Optional<AuditTrailRecord> record = auditTrailService.getLogsByCredential(credentialId);
        if(record.isPresent()) {
            return ResponseEntity.status(200).body(record.get());
        } else {
            return ResponseEntity.status(404).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<AuditTrailRecord>> getAllAuditTrails() {
        List<AuditTrailRecord> records = auditTrailService.getAllLogs();
        return ResponseEntity.status(200).body(records);
    }

}
