package com.example.demo.service.impl;

import com.example.demo.entity.AuditTrailRecord;
import com.example.demo.exception.BadRequestException;
import com.example.demo.repository.AuditTrailRecordRepository;
import com.example.demo.service.AuditTrailService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AuditTrailServiceImpl implements AuditTrailService {
    
    private final AuditTrailRecordRepository auditRepo;
    
    public AuditTrailServiceImpl(AuditTrailRecordRepository auditRepo) {
        this.auditRepo = auditRepo;
    }
    
    @Override
    public AuditTrailRecord logEvent(AuditTrailRecord record) {
        if (record == null) {
            throw new BadRequestException("Audit record cannot be null");
        }
        
        if (record.getLoggedAt() == null) {
            record.setLoggedAt(LocalDateTime.now());
        }
        
        return auditRepo.save(record);
    }
    
    @Override
    public List<AuditTrailRecord> getLogsByCredential(Long credentialId) {
        if (credentialId == null) {
            throw new BadRequestException("Credential ID cannot be null");
        }
        
        return auditRepo.findByCredentialId(credentialId);
    }
    
    @Override
    public List<AuditTrailRecord> getAllLogs() {
        return auditRepo.findAll();
    }
}