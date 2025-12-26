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

    @GetMapping("/credential/{credentialId}")
    public ResponseEntity<List<AuditTrailRecord>> getByCredential(
            @PathVariable Long credentialId) {

        return ResponseEntity.ok(
                service.getLogsByCredential(credentialId)
        );
    }
}
