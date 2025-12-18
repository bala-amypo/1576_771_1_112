package com.example.demo.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import com.example.demo.entity.AuditTrailRecord;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.AuditTrailRecordRepository;
import com.example.demo.service.AuditTrailService;

@Service
@RequiredArgsConstructor
public class AuditTrailServiceImpl implements AuditTrailService {

    private final AuditTrailRecordRepository repo;

    @Override
    public AuditTrailRecord logEvent(AuditTrailRecord record){
        if(record.getLoggedAt() == null)
            record.setLoggedAt(LocalDateTime.now());
        return repo.save(record);
    }

    @Override
	public List<AuditTrailRecord> getLogsByCredential(Long credentialId) {
		boolean exists = repo.existsById(credentialId);
        if(!exists)
            throw new ResourceNotFoundException("Credential ID not found");
        return repo.findByCredentialId(credentialId);
    }

    @Override
    public List<AuditTrailRecord> getAllLogs() {
        return repo.findAll();
    }
}
