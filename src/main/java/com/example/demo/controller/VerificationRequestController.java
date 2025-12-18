package com.example.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.VerificationRequest;
import com.example.demo.service.VerificationRequestService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/verification")
@RequiredArgsConstructor
public class VerificationRequestController {

    private final VerificationRequestService service;

    @PostMapping
    public ResponseEntity<VerificationRequest> create(
            @RequestBody VerificationRequest request){

        return ResponseEntity
                .status(201)
                .body(service.createRequest(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<VerificationRequest> getById(
            @PathVariable Long id){

        return ResponseEntity.ok(service.getRequestById(id));
    }

    @GetMapping("/credential/{credentialId}")
    public ResponseEntity<List<VerificationRequest>> getByCredential(
            @PathVariable Long credentialId){

        return ResponseEntity.ok(
                service.getRequestsByCredential(credentialId));
    }

    @PostMapping("/{id}/process")
    public ResponseEntity<VerificationRequest> process(
            @PathVariable Long id){

        return ResponseEntity.ok(
                service.processVerification(id));
    }
}
