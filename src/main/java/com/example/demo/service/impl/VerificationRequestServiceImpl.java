package com.example.demo.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.AuditTrailRecord;
import com.example.demo.entity.CredentialRecord;
import com.example.demo.entity.VerificationRequest;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.AuditTrailRecordRepository;
import com.example.demo.repository.CredentialRecordRepository;
import com.example.demo.repository.VerificationRequestRepository;
import com.example.demo.service.VerificationRequestService;

@Service
public class VerificationRequestServiceImpl
        implements VerificationRequestService {

    private final VerificationRequestRepository requestRepo;
    private final CredentialRecordRepository credentialRepo;
    private final AuditTrailRecordRepository auditRepo;

    // ✅ Spring constructor
    @Autowired
    public VerificationRequestServiceImpl(
            VerificationRequestRepository requestRepo,
            CredentialRecordRepository credentialRepo,
            AuditTrailRecordRepository auditRepo) {

        this.requestRepo = requestRepo;
        this.credentialRepo = credentialRepo;
        this.auditRepo = auditRepo;
    }

    @Override
    public VerificationRequest initiateVerification(VerificationRequest request) {
        request.setStatus("PENDING");
        return requestRepo.save(request);
    }

    @Override
    public VerificationRequest getRequestById(Long id) {
        return requestRepo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Verification request not found"));
    }

    @Override
    public List<VerificationRequest> getRequestsByCredential(Long credentialId) {
        return requestRepo.findByCredentialId(credentialId);
    }

    @Override
    public VerificationRequest processVerification(Long requestId) {

        VerificationRequest request = getRequestById(requestId);

        CredentialRecord credential = credentialRepo
                .findById(request.getCredentialId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Credential not found"));

        if (credential.getExpiryDate() != null &&
                credential.getExpiryDate().isBefore(LocalDate.now())) {

            request.setStatus("FAILED");
        } else {
            request.setStatus("SUCCESS");
        }

        request.setVerifiedAt(LocalDateTime.now());

        // ✅ AUDIT LOG (repository-based, test-safe)
        AuditTrailRecord audit = new AuditTrailRecord(
                credential.getId(),
                "Verification processed: " + request.getStatus()
        );
        auditRepo.save(audit);

        return requestRepo.save(request);
    }
}
