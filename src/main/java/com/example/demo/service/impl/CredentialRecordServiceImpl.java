package com.example.demo.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.CredentialRecord;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.CredentialRecordRepository;
import com.example.demo.service.CredentialRecordService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CredentialRecordServiceImpl
        implements CredentialRecordService {

    private final CredentialRecordRepository credentialRepo;

    @Override
    public CredentialRecord createCredential(CredentialRecord record) {

        if (record.getExpiryDate() != null &&
            record.getExpiryDate().isBefore(LocalDate.now())) {

            record.setStatus("EXPIRED");
        }

        if (record.getStatus() == null) {
            record.setStatus("VALID");
        }

        return credentialRepo.save(record);
    }

    @Override
    public CredentialRecord updateCredential(
            Long id,
            CredentialRecord update) {

        CredentialRecord existing =
                credentialRepo.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Credential not found"));

        existing.setCredentialCode(update.getCredentialCode());
        existing.setTitle(update.getTitle());
        existing.setIssuer(update.getIssuer());
        existing.setCredentialType(update.getCredentialType());
        existing.setExpiryDate(update.getExpiryDate());
        existing.setMetadataJson(update.getMetadataJson());

        if (existing.getExpiryDate() != null &&
            existing.getExpiryDate().isBefore(LocalDate.now())) {

            existing.setStatus("EXPIRED");
        }

        return credentialRepo.save(existing);
    }

    @Override
    public List<CredentialRecord> getCredentialsByHolder(Long holderId) {
        return credentialRepo.findByHolderId(holderId);
    }

    @Override
    public CredentialRecord getCredentialByCode(String code) {
        return credentialRepo.findByCredentialCode(code)
                .orElse(null);
    }

    @Override
    public List<CredentialRecord> getAllCredentials() {
        return credentialRepo.findAll();
    }
}
