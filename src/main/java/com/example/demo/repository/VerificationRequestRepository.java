package com.example.demo.repository;

import com.example.demo.entity.VerificationRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VerificationRequestRepository extends JpaRepository<VerificationRequest, Long> {
    
    Optional<VerificationRequest> findById(Long id);
    
    List<VerificationRequest> findByCredentialId(Long credentialId);
}