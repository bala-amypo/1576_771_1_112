package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.*;
import com.example.demo.service.*;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VerificationRequestServiceImpl implements VerificationRequestService {

    private final VerificationRequestRepository requestRepo;
    private final CredentialRecordRepository credentialRepo;
    private final VerificationRuleRepository ruleRepo;
    private final AuditTrailRecordRepository auditRepo;

    public VerificationRequest initiateVerification(VerificationRequest r) {
        return requestRepo.save(r);
    }

    public VerificationRequest processVerification(Long id) {
        VerificationRequest req = requestRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found"));

        CredentialRecord c = credentialRepo.findAll().stream()
                .filter(x -> x.getId().equals(req.getCredentialId()))
                .findFirst().orElse(null);

        boolean expired = c != null &&
                c.getExpiryDate() != null &&
                c.getExpiryDate().isBefore(LocalDate.now());

        req.setStatus(expired ? "FAILED" : "SUCCESS");
        requestRepo.save(req);

        auditRepo.save(new AuditTrailRecord(
                null, req.getCredentialId(), "Verification " + req.getStatus(), LocalDateTime.now()
        ));

        return req;
    }

    public List<VerificationRequest> getRequestsByCredential(Long id) {
        return requestRepo.findByCredentialId(id);
    }
}
