package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.VerificationRequest;

public interface VerificationRequestRepository
        extends JpaRepository<VerificationRequest, Long> {

    Optional<VerificationRequest> findById(Long id);

    List<VerificationRequest> findByCredentialId(Long credentialId);
}
