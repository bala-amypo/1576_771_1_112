package com.example.demo.service.impl;

import com.example.demo.entity.CredentialRecord;
import com.example.demo.repository.CredentialRecordRepository;
import com.example.demo.service.CredentialRecordService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CredentialRecordServiceImpl implements CredentialRecordService {

    private final CredentialRecordRepository repository;

    @Override
    public CredentialRecord createCredential(CredentialRecord record) {
        if (record.getExpiryDate() != null &&
            record.getExpiryDate().isBefore(LocalDate.now())) {
            record.setStatus("EXPIRED");
        } else if (record.getStatus() == null) {
            record.setStatus("VALID");
        }
        return repository.save(record);
    }

    @Override
    public CredentialRecord updateCredential(Long id, CredentialRecord record) {
        CredentialRecord existing = repository.findById(id).orElseThrow();
        if (record.getCredentialCode() != null) {
            existing.setCredentialCode(record.getCredentialCode());
        }
        return repository.save(existing);
    }

    @Override
    public List<CredentialRecord> getCredentialsByHolder(Long holderId) {
        return repository.findByHolderId(holderId);
    }

    // ✅ IMPLEMENT IT
    @Override
    public CredentialRecord getByCredentialCode(String credentialCode) {
        return repository.findByCredentialCode(credentialCode).orElse(null);
    }

    @Override
    public List<CredentialRecord> getAllCredentials() {
        return repository.findAll();
    }

}
