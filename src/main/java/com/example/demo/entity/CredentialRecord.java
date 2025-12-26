package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "credential_records")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CredentialRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long holderId;
    private String credentialCode;
    private String title;
    private String issuer;
    private String credentialType;
    private String status;
    private LocalDate expiryDate;
    private String metadataJson;

    @ManyToMany
    @Builder.Default
    private Set<VerificationRule> rules = new HashSet<>();
}
