package com.example.demo.service.impl;

import com.example.demo.entity.AuditTrailRecord;
import com.example.demo.repository.AuditTrailRecordRepository;
import com.example.demo.service.AuditTrailService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuditTrailServiceImpl implements AuditTrailService {

    private final AuditTrailRecordRepository auditRepo;

    @Override
    public AuditTrailRecord logEvent(AuditTrailRecord record) {

        if (record.getLoggedAt() == null) {
            record.setLoggedAt(LocalDateTime.now());
        }

        return auditRepo.save(record);
    }

    @Override
    public List<AuditTrailRecord> getLogsByCredential(Long credentialId) {
        return auditRepo.findByCredentialId(credentialId);
    }
}
