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
        try {
            if (request == null) {
                request = new VerificationRequest();
            }
            return verificationRequestRepo.save(request);
        } catch (Exception e) {
            throw new RuntimeException("Failed to initiate verification: " + e.getMessage(), e);
        }
    }
    
    @Override
    public VerificationRequest processVerification(Long requestId) {
        try {
            if (requestId == null) {
                throw new ResourceNotFoundException("Request ID cannot be null");
            }
            
            VerificationRequest request = verificationRequestRepo.findById(requestId)
                    .orElseThrow(() -> new ResourceNotFoundException("Verification request not found with id: " + requestId));
            
            // Find the credential
            CredentialRecord credential = null;
            try {
                List<CredentialRecord> allCredentials = credentialService.getAllCredentials();
                for (CredentialRecord c : allCredentials) {
                    if (c.getId() != null && c.getId().equals(request.getCredentialId())) {
                        credential = c;
                        break;
                    }
                }
            } catch (Exception e) {
                // Credential retrieval failed
            }
            
            if (credential == null) {
                request.setStatus("FAILED");
                request.setResultMessage("Credential not found");
            } else {
                try {
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
                } catch (Exception e) {
                    request.setStatus("FAILED");
                    request.setResultMessage("Verification processing error");
                }
            }
            
            request.setVerifiedAt(LocalDateTime.now());
            
            // Log audit event
            try {
                AuditTrailRecord audit = new AuditTrailRecord();
                audit.setCredentialId(request.getCredentialId());
                audit.setEventType("VERIFICATION");
                audit.setDetails("Verification processed with status: " + request.getStatus());
                auditService.logEvent(audit);
            } catch (Exception e) {
            }
            
            return verificationRequestRepo.save(request);
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Failed to process verification: " + e.getMessage(), e);
        }
    }
    
    @Override
    public List<VerificationRequest> getRequestsByCredential(Long credentialId) {
        try {
            if (credentialId == null) {
                return List.of();
            }
            return verificationRequestRepo.findByCredentialId(credentialId);
        } catch (Exception e) {
            return List.of();
        }
    }
    
    @Override
    public List<VerificationRequest> getAllRequests() {
        try {
            return verificationRequestRepo.findAll();
        } catch (Exception e) {
            return List.of();
        }
    }
}