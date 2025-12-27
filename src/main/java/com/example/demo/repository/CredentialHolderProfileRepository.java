package com.example.demo.repository;

import com.example.demo.entity.CredentialHolderProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CredentialHolderProfileRepository extends JpaRepository<CredentialHolderProfile, Long> {
    
    Optional<CredentialHolderProfile> findById(Long id);
    
    Optional<CredentialHolderProfile> findByHolderId(String holderId);
}