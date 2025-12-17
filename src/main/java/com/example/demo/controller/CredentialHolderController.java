package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.CredentialHolderProfile;
import com.example.demo.service.CredentialHolderProfileService;

import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/api/holders")
@Tag(name = "Credential Holder Controller", description = "APIs for managing credential holders")

public class CredentialHolderController {
    
    @Autowired
    CredentialHolderProfileService credentialHolderProfileService;

    @PostMapping
    public ResponseEntity<CredentialHolderProfile> createHolder(@RequestBody CredentialHolderProfile credentialHolderProfile) {
        CredentialHolderProfile createdHolder = credentialHolderProfileService.createHolder(credentialHolderProfile);
        return ResponseEntity.status(201).body(createdHolder);
    }

    @GetMapping
    public ResponseEntity<List<CredentialHolderProfile>> getAllHolders() {
        List<CredentialHolderProfile> holders = credentialHolderProfileService.getAllHolders();
        return ResponseEntity.status(200).body(holders);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CredentialHolderProfile> getHolderbyId(@PathVariable Long id){
        Optional<CredentialHolderProfile> holder = Optional.ofNullable(credentialHolderProfileService.getHolderById(id));
        if(holder.isPresent()){
            return ResponseEntity.status(200).body(holder.get());
        }
        return ResponseEntity.status(404).build();
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<String> updateHolderStatus(@PathVariable Long id, @RequestBody CredentialHolderProfile credentialHolderProfile){
        CredentialHolderProfile updatedHolder = credentialHolderProfileService.updateHolderStatus(id, credentialHolderProfile.isActive());
        return ResponseEntity.status(200).body("Holder status updated to: " + updatedHolder.isActive());
    }

}
