package com.example.demo.service.impl;

import com.example.demo.entity.CredentialHolderProfile;
import com.example.demo.exception.BadRequestException;
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
        if (profile == null) {
            throw new BadRequestException("Holder profile cannot be null");
        }
        
        return holderRepo.save(profile);
    }
    
    @Override
    public CredentialHolderProfile getHolderById(Long id) {
        if (id == null) {
            throw new BadRequestException("Holder ID cannot be null");
        }
        
        return holderRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Holder not found with id: " + id));
    }
    
    @Override
    public List<CredentialHolderProfile> getAllHolders() {
        return holderRepo.findAll();
    }
    
    @Override
    public CredentialHolderProfile findByHolderId(String holderId) {
        if (holderId == null || holderId.trim().isEmpty()) {
            throw new BadRequestException("Holder ID cannot be null or empty");
        }
        
        return holderRepo.findByHolderId(holderId)
                .orElseThrow(() -> new ResourceNotFoundException("Holder not found with holderId: " + holderId));
    }
    
    @Override
    public CredentialHolderProfile updateHolderStatus(Long id, boolean active) {
        if (id == null) {
            throw new BadRequestException("Holder ID cannot be null");
        }
        
        CredentialHolderProfile profile = getHolderById(id);
        profile.setActive(active);
        return holderRepo.save(profile);
    }
}