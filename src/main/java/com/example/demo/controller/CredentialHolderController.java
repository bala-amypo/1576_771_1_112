package com.example.demo.controller;

import java.util.List;

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
import com.example.demo.service.CredentialHolderService;


@RestController
@RequestMapping("/api/holders")
public class CredentialHolderController {
    
    @Autowired
    CredentialHolderService credentialHolderService;

    @PostMapping
    public ResponseEntity<CredentialHolderProfile> createHolder(@RequestBody CredentialHolderProfile credentialHolderProfile) {
        CredentialHolderProfile createdHolder = credentialHolderService.createHolder(credentialHolderProfile);
        return ResponseEntity.status(201).body(createdHolder);
    }

    @GetMapping
    public ResponseEntity<List<CredentialHolderProfile>> getAllHolders() {
        List<CredentialHolderProfile> holders = credentialHolderService.getAllHolders();
        return ResponseEntity.status(200).body(holders);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CredentialHolderProfile> getHolderbyId(@PathVariable int id){
        CredentialHolderProfile holder = credentialHolderService.getHolderById(id);
        return ResponseEntity.status(200).body(holder);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<String> updateHolderStatus(@PathVariable int id, @RequestBody CredentialHolderProfile credentialHolderProfile){
        if(credentialHolderService.updateHolderStatus(id,credentialHolderProfile)!=null){
            return ResponseEntity.status(200).body("Status Active updated Succesfully");
        }else{
            return ResponseEntity.status(404).build();
        }
    }

}

//Check needed