package com.example.demo.controller;

import com.example.demo.entity.CredentialRecord;
import com.example.demo.service.CredentialRecordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/credentials")
@Tag(name = "Credentials", description = "Credential management endpoints")
public class CredentialRecordController {
    
    private final CredentialRecordService credentialService;
    
    public CredentialRecordController(CredentialRecordService credentialService) {
        this.credentialService = credentialService;
    }
    
    @PostMapping
    @Operation(summary = "Create credential")
    public ResponseEntity<CredentialRecord> create(@RequestBody CredentialRecord record) {
        return ResponseEntity.ok(credentialService.createCredential(record));
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Update credential")
    public ResponseEntity<CredentialRecord> update(@PathVariable Long id, @RequestBody CredentialRecord updated) {
        return ResponseEntity.ok(credentialService.updateCredential(id, updated));
    }
    
    @GetMapping("/holder/{holderId}")
    @Operation(summary = "Get credentials for holder")
    public ResponseEntity<List<CredentialRecord>> getByHolder(@PathVariable Long holderId) {
        return ResponseEntity.ok(credentialService.getCredentialsByHolder(holderId));
    }
    
    @GetMapping("/code/{credentialCode}")
    @Operation(summary = "Find credential by code")
    public ResponseEntity<CredentialRecord> getByCode(@PathVariable String credentialCode) {
        return ResponseEntity.ok(credentialService.getCredentialByCode(credentialCode));
    }
    
    @GetMapping
    @Operation(summary = "List all credentials")
    public ResponseEntity<List<CredentialRecord>> getAll() {
        return ResponseEntity.ok(credentialService.getAllCredentials());
    }
}