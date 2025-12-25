package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.VerificationRequest;

public interface VerificationRequestService {

    VerificationRequest initiateVerification(VerificationRequest request);

    VerificationRequest getRequestById(Long id);

    VerificationRequest processVerification(Long requestId);

    List<VerificationRequest> getRequestsByCredential(Long credentialId);
}
