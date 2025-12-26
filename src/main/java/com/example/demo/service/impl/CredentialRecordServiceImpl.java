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
public class CredentialRecordServiceImpl
        implements CredentialRecordService {

    private final CredentialRecordRepository repository;

    @Override
    public CredentialRecord createCredential(CredentialRecord record) {
        if (record.getStatus() == null) {
            record.setStatus("VALID");
        }
        if (record.getExpiryDate() != null &&
            record.getExpiryDate().isBefore(LocalDate.now())) {
            record.setStatus("EXPIRED");
        }
        return repository.save(record);
    }

    @Override
    public CredentialRecord updateCredential(Long id, CredentialRecord updated) {
        CredentialRecord existing = repository.findById(id).orElseThrow();
        if (updated.getCredentialCode() != null)
            existing.setCredentialCode(updated.getCredentialCode());
        return repository.save(existing);
    }

    @Override
    public List<CredentialRecord> getCredentialsByHolder(Long holderId) {
        return repository.findByHolderId(holderId);
    }

    @Override
    public CredentialRecord getCredentialByCode(String code) {
        return repository.findByCredentialCode(code).orElse(null);
    }

    @Override
    public List<CredentialRecord> getAllCredentials() {
        return repository.findAll();
    }
}
