package com.example.demo.controller;

import com.example.demo.entity.VerificationRequest;
import com.example.demo.service.VerificationRequestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/verification")
@Tag(name = "Verification Requests", description = "Verification request endpoints")
public class VerificationRequestController {
    
    private final VerificationRequestService verificationService;
    
    public VerificationRequestController(VerificationRequestService verificationService) {
        this.verificationService = verificationService;
    }
    
    @PostMapping
    @Operation(summary = "Initiate verification")
    public ResponseEntity<VerificationRequest> initiate(@RequestBody VerificationRequest request) {
        return ResponseEntity.ok(verificationService.initiateVerification(request));
    }
    
    @PutMapping("/{id}/process")
    @Operation(summary = "Process/validate a request")
    public ResponseEntity<VerificationRequest> process(@PathVariable Long id) {
        return ResponseEntity.ok(verificationService.processVerification(id));
    }
    
    @GetMapping("/credential/{credentialId}")
    @Operation(summary = "Get requests for a credential")
    public ResponseEntity<List<VerificationRequest>> getByCredential(@PathVariable Long credentialId) {
        return ResponseEntity.ok(verificationService.getRequestsByCredential(credentialId));
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Get request by ID")
    public ResponseEntity<VerificationRequest> getById(@PathVariable Long id) {
        return ResponseEntity.ok(verificationService.getAllRequests().stream()
                .filter(r -> r.getId().equals(id))
                .findFirst()
                .orElse(null));
    }
    
    @GetMapping
    @Operation(summary = "List all verification requests")
    public ResponseEntity<List<VerificationRequest>> getAll() {
        return ResponseEntity.ok(verificationService.getAllRequests());
    }
}