package com.example.demo.service.impl;

import com.example.demo.entity.AuditTrailRecord;
import com.example.demo.entity.CredentialRecord;
import com.example.demo.entity.VerificationRequest;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.VerificationRequestRepository;
import com.example.demo.service.AuditTrailService;
import com.example.demo.service.CredentialRecordService;
import com.example.demo.service.VerificationRequestService;
import com.example.demo.service.VerificationRuleService;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class VerificationRequestServiceImpl
        implements VerificationRequestService {

    private final VerificationRequestRepository verificationRequestRepo;
    private final CredentialRecordService credentialService;
    private final VerificationRuleService ruleService;
    private final AuditTrailService auditService;

    public VerificationRequestServiceImpl(
            VerificationRequestRepository verificationRequestRepo,
            CredentialRecordService credentialService,
            VerificationRuleService ruleService,
            AuditTrailService auditService) {

        this.verificationRequestRepo = verificationRequestRepo;
        this.credentialService = credentialService;
        this.ruleService = ruleService;
        this.auditService = auditService;
    }

    @Override
    public VerificationRequest initiateVerification(VerificationRequest request) {
        return verificationRequestRepo.save(request);
    }

    @Override
    public VerificationRequest processVerification(Long requestId) {

        VerificationRequest request = verificationRequestRepo.findById(requestId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Request not found"));

        CredentialRecord credential = credentialService.getAllCredentials()
                .stream()
                .filter(c -> c.getId().equals(request.getCredentialId()))
                .findFirst()
                .orElse(null);

        boolean expired = credential != null
                && credential.getExpiryDate() != null
                && credential.getExpiryDate().isBefore(LocalDate.now());

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

    @Override
    public List<VerificationRequest> getAllRequests() {
        return verificationRequestRepo.findAll();
    }
}
