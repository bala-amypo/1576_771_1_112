package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.CredentialHolderProfile;

public interface CredentialHolderProfileRepository extends JpaRepository<CredentialHolderProfile, Long> {

    Optional<CredentialHolderProfile> findByHolderId(Long holderId);

    boolean existsByEmail(String email);

}
