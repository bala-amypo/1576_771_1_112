package com.example.demo.service;

import com.example.demo.entity.*;
import java.util.*;

public interface VerificationRequestService {
    VerificationRequest initiateVerification(VerificationRequest request);
    VerificationRequest processVerification(Long requestId);
    List<VerificationRequest> getRequestsByCredential(Long credentialId);
}

