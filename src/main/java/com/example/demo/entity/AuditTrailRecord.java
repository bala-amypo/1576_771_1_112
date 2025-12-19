package com.example.demo.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "audit_trail_records")
public class AuditTrailRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long credentialId;

    private String action;

    private LocalDateTime loggedAt;

    @PrePersist
    protected void onCreate() {
        loggedAt = LocalDateTime.now();
    }

    public AuditTrailRecord() {}

    public AuditTrailRecord(Long credentialId, String action) {
        this.credentialId = credentialId;
        this.action = action;
    }

    public Long getId() { return id; }
    public Long getCredentialId() { return credentialId; }
    public String getAction() { return action; }
    public LocalDateTime getLoggedAt() { return loggedAt; }

    public void setId(Long id) { this.id = id; }
    public void setCredentialId(Long credentialId) { this.credentialId = credentialId; }
    public void setAction(String action) { this.action = action; }
    public void setLoggedAt(LocalDateTime loggedAt) { this.loggedAt = loggedAt; }
}
