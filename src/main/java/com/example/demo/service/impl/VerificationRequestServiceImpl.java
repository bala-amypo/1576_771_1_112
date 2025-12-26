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

    private final VerificationRequestRepository repo;
    private final CredentialRecordService credentialService;
    private final AuditTrailService auditService;

    @Override
    public VerificationRequest initiateVerification(VerificationRequest request) {
        return repo.save(request);
    }

    @Override
    public VerificationRequest processVerification(Long requestId) {

        VerificationRequest req = repo.findById(requestId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Request not found"));

        CredentialRecord credential = credentialService.getAllCredentials().stream()
                .filter(c -> c.getId().equals(req.getCredentialId()))
                .findFirst()
                .orElse(null);

        boolean expired = credential != null &&
                credential.getExpiryDate() != null &&
                credential.getExpiryDate().isBefore(LocalDate.now());

        req.setStatus(expired ? "FAILED" : "SUCCESS");
        repo.save(req);

        auditService.logEvent(
                AuditTrailRecord.builder()
                        .credentialId(req.getCredentialId())
                        .action("Verification " + req.getStatus())
                        .loggedAt(LocalDateTime.now())
                        .build()
        );

        return req;
    }

    @Override
    public List<VerificationRequest> getRequestsByCredential(Long credentialId) {
        return repo.findByCredentialId(credentialId);
    }

    @Override
    public List<VerificationRequest> getAllRequests() {
        return repo.findAll();
    }
}
