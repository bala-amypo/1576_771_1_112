package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "verification_requests")
public class VerificationRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "credential_id", nullable = false)
    private Long credentialId;

    private String status;

    private LocalDateTime verifiedAt;

    public VerificationRequest() {}

    public VerificationRequest(Long credentialId, String status) {
        this.credentialId = credentialId;
        this.status = status;
        this.verifiedAt = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public Long getCredentialId() { return credentialId; }
    public String getStatus() { return status; }
    public LocalDateTime getVerifiedAt() { return verifiedAt; }

    public void setId(Long id) { this.id = id; }
    public void setCredentialId(Long credentialId) { this.credentialId = credentialId; }
    public void setStatus(String status) { this.status = status; }
    public void setVerifiedAt(LocalDateTime verifiedAt) { this.verifiedAt = verifiedAt; }
}
