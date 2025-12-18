package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.AuditTrailRecord;

public interface AuditTrailService {
    AuditTrailRecord logEvent(AuditTrailRecord record);
    Optional<AuditTrailRecord> getLogsByCredential(Long credentialId);
    List<AuditTrailRecord> getAllLogs();
}
