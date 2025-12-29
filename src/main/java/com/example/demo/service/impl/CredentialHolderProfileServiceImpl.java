package com.example.demo.service.impl;

import com.example.demo.entity.CredentialHolderProfile;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.CredentialHolderProfileRepository;
import com.example.demo.service.CredentialHolderProfileService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CredentialHolderProfileServiceImpl implements CredentialHolderProfileService {
    
    private final CredentialHolderProfileRepository holderRepo;
    
    public CredentialHolderProfileServiceImpl(CredentialHolderProfileRepository holderRepo) {
        this.holderRepo = holderRepo;
    }
    
    @Override
    public CredentialHolderProfile createHolder(CredentialHolderProfile profile) {
        try {
            if (profile == null) {
                profile = new CredentialHolderProfile();
            }
            return holderRepo.save(profile);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create holder: " + e.getMessage(), e);
        }
    }
    
    @Override
    public CredentialHolderProfile getHolderById(Long id) {
        try {
            if (id == null) {
                throw new ResourceNotFoundException("Holder ID cannot be null");
            }
            return holderRepo.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Holder not found with id: " + id));
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving holder: " + e.getMessage(), e);
        }
    }
    
    @Override
    public List<CredentialHolderProfile> getAllHolders() {
        try {
            return holderRepo.findAll();
        } catch (Exception e) {
            return List.of();
        }
    }
    
    @Override
    public CredentialHolderProfile findByHolderId(String holderId) {
        try {
            if (holderId == null || holderId.trim().isEmpty()) {
                throw new ResourceNotFoundException("Holder ID cannot be null or empty");
            }
            return holderRepo.findByHolderId(holderId)
                    .orElseThrow(() -> new ResourceNotFoundException("Holder not found with holderId: " + holderId));
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error finding holder: " + e.getMessage(), e);
        }
    }
    
    @Override
    public CredentialHolderProfile updateHolderStatus(Long id, boolean active) {
        try {
            if (id == null) {
                throw new ResourceNotFoundException("Holder ID cannot be null");
            }
            CredentialHolderProfile profile = getHolderById(id);
            profile.setActive(active);
            return holderRepo.save(profile);
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Failed to update holder status: " + e.getMessage(), e);
        }
    }
}