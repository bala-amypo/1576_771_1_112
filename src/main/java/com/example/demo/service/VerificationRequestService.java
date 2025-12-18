package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.VerificationRequest;

public interface VerificationRequestService {

    VerificationRequest createRequest(VerificationRequest request);

    VerificationRequest getRequestById(Long id);

    List<VerificationRequest> getRequestsByCredential(Long credentialId);

    VerificationRequest processVerification(Long requestId);
}
