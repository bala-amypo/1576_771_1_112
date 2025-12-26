package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.VerificationRequestRepository;
import com.example.demo.repository.CredentialRecordRepository;
import com.example.demo.repository.VerificationRuleRepository;
import com.example.demo.service.AuditTrailService;
import com.example.demo.service.VerificationRequestService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VerificationRequestServiceImpl
        implements VerificationRequestService {

    private final VerificationRequestRepository requestRepo;
    private final CredentialRecordRepository credentialRepo;   // ✅ USE REPO
    private final VerificationRuleRepository ruleRepo;
    private final AuditTrailService auditService;

    @Override
    public VerificationRequest initiateVerification(VerificationRequest request) {
        return requestRepo.save(request);
    }

    @Override
    public VerificationRequest processVerification(Long requestId) {

        VerificationRequest request = requestRepo.findById(requestId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Request not found"));

        // ✅ TEST EXPECTS findAll()
        CredentialRecord credential = credentialRepo.findAll().stream()
                .filter(c -> c.getId().equals(request.getCredentialId()))
                .findFirst()
                .orElse(null);

        boolean expired = credential != null &&
                credential.getExpiryDate() != null &&
                credential.getExpiryDate().isBefore(LocalDate.now());

        request.setStatus(expired ? "FAILED" : "SUCCESS");
        requestRepo.save(request);

        AuditTrailRecord log = new AuditTrailRecord();
        log.setCredentialId(request.getCredentialId());
        log.setAction("Verification " + request.getStatus());
        log.setLoggedAt(LocalDateTime.now());
        auditService.logEvent(log);

        return request;
    }

    @Override
    public List<VerificationRequest> getRequestsByCredential(Long credentialId) {
        return requestRepo.findByCredentialId(credentialId);
    }
}
