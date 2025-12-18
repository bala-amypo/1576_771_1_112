package com.example.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.CredentialRecord;
import com.example.demo.service.CredentialRecordService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/credentials")
@RequiredArgsConstructor
public class CredentialRecordController {

    private final CredentialRecordService service;

    @PostMapping
    public ResponseEntity<CredentialRecord> create(@RequestBody CredentialRecord r){
        return ResponseEntity.status(201).body(service.createCredential(r));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CredentialRecord> update(@PathVariable Long id,
                                                   @RequestBody CredentialRecord r){
        return ResponseEntity.ok(service.updateCredential(id, r));
    }

    @GetMapping("/holder/{holderId}")
    public ResponseEntity<List<CredentialRecord>> getByHolder(@PathVariable Long holderId){
        return ResponseEntity.ok(service.getCredentialsByHolder(holderId));
    }

    @GetMapping("/code/{code}")
    public ResponseEntity<CredentialRecord> getByCode(@PathVariable String code){
        CredentialRecord rec = service.getCredentialByCode(code);
        return (rec == null) ?
                ResponseEntity.notFound().build() :
                ResponseEntity.ok(rec);
    }
}
