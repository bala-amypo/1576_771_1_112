package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.CredentialHolderProfile;

@Repository
public interface CredentialHolderProfileRepository extends JpaRepository<CredentialHolderProfile, Long> {
    
}
