package com.example.demo.entity;

import java.time.LocalDateTime;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class VerificationRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long credentialId;
    private String requestedBy;
    private String verificationMethod;
    private String status;
    private LocalDateTime verifiedAt;
    private String resultMessage;
    
    public VerificationRequest(){}

    public VerificationRequest(long credentialId, String requestedBy, String resultMessage, String status, String verificationMethod, LocalDateTime verifiedAt) {
        this.credentialId = credentialId;
        this.requestedBy = requestedBy;
        this.resultMessage = resultMessage;
        this.status = status;
        this.verificationMethod = verificationMethod;
        this.verifiedAt = verifiedAt;
    }

    public long getId() {
        return id;
    }

    public long getCredentialId() {
        return credentialId;
    }

    public String getRequestedBy() {
        return requestedBy;
    }

    public String getVerificationMethod() {
        return verificationMethod;
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getVerifiedAt() {
        return verifiedAt;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setCredentialId(long credentialId) {
        this.credentialId = credentialId;
    }

    public void setRequestedBy(String requestedBy) {
        this.requestedBy = requestedBy;
    }

    public void setVerificationMethod(String verificationMethod) {
        this.verificationMethod = verificationMethod;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setVerifiedAt(LocalDateTime verifiedAt) {
        this.verifiedAt = verifiedAt;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }


}
