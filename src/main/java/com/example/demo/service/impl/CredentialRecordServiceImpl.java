package com.example.demo.service.impl;

import com.example.demo.entity.CredentialRecord;
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
        try {
            if (record == null) {
                record = new CredentialRecord();
            }
            
            // Check if expired
            if (record.getExpiryDate() != null && record.getExpiryDate().isBefore(LocalDate.now())) {
                record.setStatus("EXPIRED");
            } else if (record.getStatus() == null) {
                record.setStatus("VALID");
            }
            
            return credentialRepo.save(record);
        } catch (Exception e) {
            if (record == null) {
                record = new CredentialRecord();
            }
            if (record.getStatus() == null) {
                record.setStatus("VALID");
            }
            throw new RuntimeException("Failed to create credential: " + e.getMessage(), e);
        }
    }
    
    @Override
    public CredentialRecord updateCredential(Long id, CredentialRecord updated) {
        try {
            if (id == null) {
                throw new ResourceNotFoundException("Credential ID cannot be null");
            }
            
            if (updated == null) {
                throw new IllegalArgumentException("Updated credential cannot be null");
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
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Failed to update credential: " + e.getMessage(), e);
        }
    }
    
    @Override
    public List<CredentialRecord> getCredentialsByHolder(Long holderId) {
        try {
            if (holderId == null) {
                return List.of();
            }
            return credentialRepo.findByHolderId(holderId);
        } catch (Exception e) {
            return List.of();
        }
    }
    
    @Override
    public CredentialRecord getCredentialByCode(String code) {
        try {
            if (code == null || code.trim().isEmpty()) {
                return null;
            }
            return credentialRepo.findByCredentialCode(code).orElse(null);
        } catch (Exception e) {
            return null;
        }
    }
    
    @Override
    public List<CredentialRecord> getAllCredentials() {
        try {
            return credentialRepo.findAll();
        } catch (Exception e) {
            return List.of();
        }
    }
}