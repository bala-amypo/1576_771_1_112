package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.CredentialHolderProfile;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.CredentialHolderProfileRepository;
import com.example.demo.service.CredentialHolderProfileService;

@Service
public class CredentialHolderProfileServiceImpl implements CredentialHolderProfileService   {
    
    @Autowired
    CredentialHolderProfileRepository credHolderRepository;

    @Override
    public CredentialHolderProfile createHolder(CredentialHolderProfile profile) {
        return credHolderRepository.save(profile);
    }

    @Override
    public CredentialHolderProfile getHolderById(Long id) {
        return credHolderRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Holder not found"));
    }

    @Override
    public List<CredentialHolderProfile> getAllHolders() {
        return credHolderRepository.findAll();
    }

    @Override
    public boolean findByHolderId(String holderId) {
        return credHolderRepository.findAll().stream()
            .anyMatch(profile -> profile.getHolderId().equals(holderId));
    }

    @Override
    public CredentialHolderProfile updateHolderStatus(Long id, boolean active) {
        CredentialHolderProfile profile = credHolderRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Holder not found"));
        profile.setActive(active);
        return credHolderRepository.save(profile);
    }

}
