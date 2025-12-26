package com.example.demo.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.CredentialRecord;
import com.example.demo.repository.CredentialRecordRepository;
import com.example.demo.service.CredentialRecordService;

import lombok.RequiredArgsConstructor;

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

        if (record.getCredentialCode() != null)
            existing.setCredentialCode(record.getCredentialCode());

        return repository.save(existing);
    }

    @Override
    public List<CredentialRecord> getByHolderId(Long holderId) {
        return repository.findByHolderId(holderId);
    }

    @Override
    public CredentialRecord getByCredentialCode(String credentialCode) {
        return repository.findByCredentialCode(credentialCode).orElse(null);
    }
}
