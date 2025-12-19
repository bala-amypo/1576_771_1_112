package com.example.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.CredentialRecord;
import com.example.demo.service.CredentialRecordService;

import jakarta.validation.Valid;

import com.example.demo.exception.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/credentials")
@RequiredArgsConstructor
public class CredentialRecordController {

    private final CredentialRecordService service;

    @PostMapping
    public ResponseEntity<CredentialRecord> create(@Valid @RequestBody CredentialRecord r){
        return ResponseEntity.status(201).body(service.createCredential(r));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CredentialRecord> update(@PathVariable Long id, @Valid @RequestBody CredentialRecord r){
        return ResponseEntity.ok(service.updateCredential(id, r));
    }

    @GetMapping("/holder/{holderId}")
    public ResponseEntity<List<CredentialRecord>> getByHolder(@PathVariable Long holderId){
        List<CredentialRecord> list = service.getCredentialsByHolder(holderId);
        if(list == null || list.isEmpty()) {
            throw new ResourceNotFoundException("No credentials found for holder: " + holderId);
        }
        return ResponseEntity.ok(list);
    }

    @GetMapping("/code/{credentialCode}")
    public ResponseEntity<CredentialRecord> getByCode(@PathVariable String credentialCode){
        CredentialRecord rec = service.getCredentialByCode(credentialCode);
        if (rec == null) {
            throw new ResourceNotFoundException("Credential not found for code: " + credentialCode);
        }
        return ResponseEntity.ok(rec);
    }

    @GetMapping
    public ResponseEntity<List<CredentialRecord>> getAll(){
        List<CredentialRecord> records = service.getAllCredentials();
        if(records == null || records.isEmpty()){
            throw new ResourceNotFoundException("No credentials found");
        }
        return ResponseEntity.ok(records);
    }
}
