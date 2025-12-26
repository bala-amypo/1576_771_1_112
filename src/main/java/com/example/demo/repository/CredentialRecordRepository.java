package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.*;
import java.time.LocalDate;
import java.util.*;

public interface CredentialRecordRepository
        extends JpaRepository<CredentialRecord, Long> {

    List<CredentialRecord> findByHolderId(Long holderId);
    Optional<CredentialRecord> findByCredentialCode(String code);
    List<CredentialRecord> findExpiredBefore(LocalDate date);

    List<CredentialRecord> findByStatusUsingHql(String status);
    List<CredentialRecord> searchByIssuerAndType(String issuer, String credentialType);
}

