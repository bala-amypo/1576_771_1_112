package com.example.demo.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.AuditTrailRecord;
import com.example.demo.entity.CredentialRecord;
import com.example.demo.entity.VerificationRequest;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.AuditTrailRecordRepository;
import com.example.demo.repository.CredentialRecordRepository;
import com.example.demo.repository.VerificationRequestRepository;
import com.example.demo.service.VerificationRequestService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VerificationRequestServiceImpl
        implements VerificationRequestService {

    private final VerificationRequestRepository repo;
    private final CredentialRecordRepository credentialRepo;
    private final AuditTrailRecordRepository auditRepo;

    @Override
    public VerificationRequest initiateVerification(VerificationRequest request){
        request.setStatus("PENDING");
        return repo.save(request);
    }

    @Override
    public VerificationRequest getRequestById(Long id){
        return repo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Verification request not found"));
    }

    @Override
    public List<VerificationRequest> getRequestsByCredential(Long credentialId){
        return repo.findByCredentialId(credentialId);
    }

    @Override
    public VerificationRequest processVerification(Long requestId){

        VerificationRequest request = getRequestById(requestId);

        CredentialRecord credential = credentialRepo
                .findById(request.getCredentialId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Credential not found"));

        if (credential.getExpiryDate() != null &&
                credential.getExpiryDate().isBefore(LocalDate.now())) {

            request.setStatus("FAILED");
            request.setResultMessage("Credential expired");
        } else {
            request.setStatus("SUCCESS");
            request.setResultMessage("Credential verified successfully");
        }

        request.setVerifiedAt(LocalDateTime.now());

        AuditTrailRecord audit = new AuditTrailRecord();
        audit.setCredentialId(credential.getId());
        audit.setLoggedAt(LocalDateTime.now());
        auditRepo.save(audit);

        return repo.save(request);
    }
}
