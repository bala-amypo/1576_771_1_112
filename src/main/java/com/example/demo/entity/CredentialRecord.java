package com.example.demo.entity;

import java.time.LocalDate;
import java.util.Set;

import jakarta.persistence.*;

@Entity
@Table(name = "credential_records")
public class CredentialRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long holderId;

    @Column(unique = true)
    private String credentialCode;

    private String title;

    private String issuer;

    private String credentialType;

    private String status;

    private LocalDate expiryDate;

    @Column(columnDefinition = "TEXT")
    private String metadataJson;

    @ManyToMany
    @JoinTable(
        name = "credential_rule_map",
        joinColumns = @JoinColumn(name = "credential_id"),
        inverseJoinColumns = @JoinColumn(name = "rule_id")
    )
    private Set<VerificationRule> rules;

    public CredentialRecord() {}

    public Long getId() { return id; }
    public Long getHolderId() { return holderId; }
    public String getCredentialCode() { return credentialCode; }
    public String getTitle() { return title; }
    public String getIssuer() { return issuer; }
    public String getCredentialType() { return credentialType; }
    public String getStatus() { return status; }
    public LocalDate getExpiryDate() { return expiryDate; }
    public String getMetadataJson() { return metadataJson; }
    public Set<VerificationRule> getRules() { return rules; }

    public void setId(Long id) { this.id = id; }
    public void setHolderId(Long holderId) { this.holderId = holderId; }
    public void setCredentialCode(String credentialCode) { this.credentialCode = credentialCode; }
    public void setTitle(String title) { this.title = title; }
    public void setIssuer(String issuer) { this.issuer = issuer; }
    public void setCredentialType(String credentialType) { this.credentialType = credentialType; }
    public void setStatus(String status) { this.status = status; }
    public void setExpiryDate(LocalDate expiryDate) { this.expiryDate = expiryDate; }
    public void setMetadataJson(String metadataJson) { this.metadataJson = metadataJson; }
    public void setRules(Set<VerificationRule> rules) { this.rules = rules; }
}
