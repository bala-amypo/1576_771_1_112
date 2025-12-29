package com.example.demo.service.impl;

import com.example.demo.entity.CredentialRecord;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.CredentialRecordRepository;
import com.example.demo.service.CredentialRecordService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CredentialRecordServiceImpl implements CredentialRecordService {
    
    private final CredentialRecordRepository credentialRepo;
    
    public CredentialRecordServiceImpl(CredentialRecordRepository credentialRepo) {
        this.credentialRepo = credentialRepo;
    }
    
    @Override
    public CredentialRecord createCredential(CredentialRecord record) {
        if (record == null) {
            throw new BadRequestException("Credential record cannot be null");
        }
        
        // Check if expired
        if (record.getExpiryDate() != null && record.getExpiryDate().isBefore(LocalDate.now())) {
            record.setStatus("EXPIRED");
        } else if (record.getStatus() == null) {
            record.setStatus("VALID");
        }
        
        return credentialRepo.save(record);
    }
    
    @Override
    public CredentialRecord updateCredential(Long id, CredentialRecord updated) {
        if (id == null) {
            throw new BadRequestException("Credential ID cannot be null");
        }
        
        if (updated == null) {
            throw new BadRequestException("Updated credential cannot be null");
        }
        
        CredentialRecord existing = credentialRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Credential not found with id: " + id));
        
        if (updated.getCredentialCode() != null) {
            existing.setCredentialCode(updated.getCredentialCode());
        }
        if (updated.getTitle() != null) {
            existing.setTitle(updated.getTitle());
        }
        if (updated.getIssuer() != null) {
            existing.setIssuer(updated.getIssuer());
        }
        if (updated.getCredentialType() != null) {
            existing.setCredentialType(updated.getCredentialType());
        }
        if (updated.getStatus() != null) {
            existing.setStatus(updated.getStatus());
        }
        if (updated.getExpiryDate() != null) {
            existing.setExpiryDate(updated.getExpiryDate());
        }
        if (updated.getIssueDate() != null) {
            existing.setIssueDate(updated.getIssueDate());
        }
        if (updated.getMetadataJson() != null) {
            existing.setMetadataJson(updated.getMetadataJson());
        }
        if (updated.getHolderId() != null) {
            existing.setHolderId(updated.getHolderId());
        }
        
        return credentialRepo.save(existing);
    }
    
    @Override
    public List<CredentialRecord> getCredentialsByHolder(Long holderId) {
        if (holderId == null) {
            throw new BadRequestException("Holder ID cannot be null");
        }
        
        return credentialRepo.findByHolderId(holderId);
    }
    
    @Override
    public CredentialRecord getCredentialByCode(String code) {
        if (code == null || code.trim().isEmpty()) {
            throw new BadRequestException("Credential code cannot be null or empty");
        }
        
        return credentialRepo.findByCredentialCode(code).orElse(null);
    }
    
    @Override
    public List<CredentialRecord> getAllCredentials() {
        return credentialRepo.findAll();
    }
}