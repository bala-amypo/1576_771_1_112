package com.example.demo.service.impl;

import com.example.demo.entity.AuditTrailRecord;
import com.example.demo.entity.CredentialRecord;
import com.example.demo.entity.VerificationRequest;
import com.example.demo.entity.VerificationRule;
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
public class VerificationRequestServiceImpl implements VerificationRequestService {
    
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
                .orElseThrow(() -> new ResourceNotFoundException("Verification request not found with id: " + requestId));
        
        // Find the credential
        CredentialRecord credential = null;
        List<CredentialRecord> allCredentials = credentialService.getAllCredentials();
        for (CredentialRecord c : allCredentials) {
            if (c.getId().equals(request.getCredentialId())) {
                credential = c;
                break;
            }
        }
        
        if (credential == null) {
            request.setStatus("FAILED");
            request.setResultMessage("Credential not found");
        } else {
            // Fetch active rules
            List<VerificationRule> activeRules = ruleService.getActiveRules();
            
            // Check if credential is expired
            if (credential.getExpiryDate() != null && credential.getExpiryDate().isBefore(LocalDate.now())) {
                request.setStatus("FAILED");
                request.setResultMessage("Credential is expired");
            } else {
                request.setStatus("SUCCESS");
                request.setResultMessage("Verification successful");
            }
        }
        
        request.setVerifiedAt(LocalDateTime.now());
        
        // Log audit event
        AuditTrailRecord audit = new AuditTrailRecord();
        audit.setCredentialId(request.getCredentialId());
        audit.setEventType("VERIFICATION");
        audit.setDetails("Verification processed with status: " + request.getStatus());
        auditService.logEvent(audit);
        
        return verificationRequestRepo.save(request);
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