package com.example.demo.controller;

import com.example.demo.entity.VerificationRequest;
import com.example.demo.service.VerificationRequestService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/verifications")
@RequiredArgsConstructor
public class VerificationRequestController {

    private final VerificationRequestService service;

    @PostMapping
    public ResponseEntity<VerificationRequest> initiate(
            @RequestBody VerificationRequest request) {
        return ResponseEntity.ok(service.initiateVerification(request));
    }

    @PutMapping("/{id}/process")
    public ResponseEntity<VerificationRequest> process(
            @PathVariable Long id) {
        return ResponseEntity.ok(service.processVerification(id));
    }

    @GetMapping("/credential/{credentialId}")
    public ResponseEntity<List<VerificationRequest>> getByCredential(
            @PathVariable Long credentialId) {
        return ResponseEntity.ok(
                service.getRequestsByCredential(credentialId));
    }

    // ✅ FIXED: STEP-4 DOES NOT REQUIRE getById
    // (Removed to avoid mismatch)

    @GetMapping
    public ResponseEntity<List<VerificationRequest>> getAll() {
        return ResponseEntity.ok(service.getAllRequests());
    }
}
