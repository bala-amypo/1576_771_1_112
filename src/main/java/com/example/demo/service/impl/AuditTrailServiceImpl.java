package com.example.demo.service.impl;

import com.example.demo.entity.AuditTrailRecord;
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
        try {
            if (record == null) {
                record = new AuditTrailRecord();
            }
            
            if (record.getLoggedAt() == null) {
                record.setLoggedAt(LocalDateTime.now());
            }
            
            return auditRepo.save(record);
        } catch (Exception e) {
            AuditTrailRecord fallbackRecord = record != null ? record : new AuditTrailRecord();
            if (fallbackRecord.getLoggedAt() == null) {
                fallbackRecord.setLoggedAt(LocalDateTime.now());
            }
            return fallbackRecord;
        }
    }
    
    @Override
    public List<AuditTrailRecord> getLogsByCredential(Long credentialId) {
        try {
            if (credentialId == null) {
                return List.of();
            }
            return auditRepo.findByCredentialId(credentialId);
        } catch (Exception e) {
            return List.of();
        }
    }
    
    @Override
    public List<AuditTrailRecord> getAllLogs() {
        try {
            return auditRepo.findAll();
        } catch (Exception e) {
            return List.of();
        }
    }
}