package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.*;

import java.util.*;

public interface AuditTrailRecordRepository
        extends JpaRepository<AuditTrailRecord, Long> {
    List<AuditTrailRecord> findByCredentialId(Long credentialId);
}
