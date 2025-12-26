package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.*;
import java.util.*;


public interface VerificationRequestRepository
        extends JpaRepository<VerificationRequest, Long> {
    List<VerificationRequest> findByCredentialId(Long credentialId);
}

