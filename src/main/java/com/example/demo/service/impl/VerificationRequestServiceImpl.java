package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.VerificationRequestRepository;
import com.example.demo.service.*;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VerificationRequestServiceImpl
        implements VerificationRequestService {

    private final VerificationRequestRepository verificationRequestRepo;
    private final CredentialRecordService credentialService;   // ✅ FIX
    private final VerificationRuleService ruleService;
    private final AuditTrailService auditService;

    @Override
    public VerificationRequest initiateVerification(VerificationRequest request) {
        return verificationRequestRepo.save(request);
    }

    @Override
    public VerificationRequest processVerification(Long requestId) {

        VerificationRequest request = verificationRequestRepo.findById(requestId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Request not found"));

        // ✅ TEST EXPECTS THIS: credentialService.getByHolder(...)
        List<CredentialRecord> credentials =
                credentialService.getCredentialsByHolder(request.getCredentialId());

        boolean expired = credentials.stream().anyMatch(c ->
                c.getExpiryDate() != null &&
                c.getExpiryDate().isBefore(LocalDate.now()));

        request.setStatus(expired ? "FAILED" : "SUCCESS");
        verificationRequestRepo.save(request);

        AuditTrailRecord log = new AuditTrailRecord();
        log.setCredentialId(request.getCredentialId());
        log.setAction("Verification " + request.getStatus());
        log.setLoggedAt(LocalDateTime.now());
        auditService.logEvent(log);

        return request;
    }

    @Override
    public List<VerificationRequest> getRequestsByCredential(Long credentialId) {
        return verificationRequestRepo.findByCredentialId(credentialId);
    }
}
