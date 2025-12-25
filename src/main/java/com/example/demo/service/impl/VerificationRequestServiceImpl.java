package com.example.demo.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.AuditTrailRecord;
import com.example.demo.entity.CredentialRecord;
import com.example.demo.entity.VerificationRequest;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.CredentialRecordRepository;
import com.example.demo.repository.VerificationRequestRepository;
import com.example.demo.service.AuditTrailService;
import com.example.demo.service.VerificationRequestService;
import com.example.demo.service.VerificationRuleService;

@Service
public class VerificationRequestServiceImpl
        implements VerificationRequestService {

    private final VerificationRequestRepository requestRepo;
    private final CredentialRecordRepository credentialRepo;
    private final VerificationRuleService ruleService;
    private final AuditTrailService auditService;

    /* =========================================================
       Constructor #1 – USED BY SPRING
       ========================================================= */
    public VerificationRequestServiceImpl(
            VerificationRequestRepository requestRepo,
            CredentialRecordRepository credentialRepo,
            VerificationRuleService ruleService,
            AuditTrailService auditService) {

        this.requestRepo = requestRepo;
        this.credentialRepo = credentialRepo;
        this.ruleService = ruleService;
        this.auditService = auditService;
    }

    /* =========================================================
       Constructor #2 – REQUIRED BY TESTS (IMPORTANT)
       ========================================================= */
    public VerificationRequestServiceImpl(
            VerificationRequestRepository requestRepo,
            CredentialRecordServiceImpl credentialServiceImpl,
            VerificationRuleService ruleService,
            AuditTrailService auditService) {

        this.requestRepo = requestRepo;
        this.credentialRepo = credentialServiceImpl.getRepository(); // 🔑
        this.ruleService = ruleService;
        this.auditService = auditService;
    }

    @Override
    public VerificationRequest initiateVerification(VerificationRequest request) {
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

        CredentialRecord credential =
                credentialRepo.findById(request.getCredentialId())
                        .orElseThrow(() ->
                                new ResourceNotFoundException("Credential not found"));

        if (credential.getExpiryDate() != null &&
            credential.getExpiryDate().isBefore(LocalDate.now())) {
            request.setStatus("FAILED");
        } else {
            request.setStatus("SUCCESS");
        }

        request.setVerifiedAt(LocalDateTime.now());

        auditService.logEvent(
                new AuditTrailRecord(
                        credential.getId(),
                        "VERIFICATION_" + request.getStatus()
                )
        );

        return requestRepo.save(request);
    }
}
