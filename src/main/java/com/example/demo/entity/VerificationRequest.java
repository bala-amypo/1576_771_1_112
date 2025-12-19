package com.example.demo.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "verification_requests")
public class VerificationRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long credentialId;

    private String requestedBy;

    private String verificationMethod;

    private String status;

    private LocalDateTime verifiedAt;

    private String resultMessage;

    public VerificationRequest(){}

    public VerificationRequest(Long credentialId, String requestedBy, String verificationMethod, String status, String resultMessage) {
        this.credentialId = credentialId;
        this.requestedBy = requestedBy;
        this.verificationMethod = verificationMethod;
        this.status = status;
        this.resultMessage = resultMessage;
        this.verifiedAt = LocalDateTime.now();
    }

    public Long getId(){ return id; }
    public Long getCredentialId(){ return credentialId; }
    public String getRequestedBy(){ return requestedBy; }
    public String getVerificationMethod(){ return verificationMethod; }
    public String getStatus(){ return status; }
    public LocalDateTime getVerifiedAt(){ return verifiedAt; }
    public String getResultMessage(){ return resultMessage; }

    public void setId(Long id){ this.id = id; }
    public void setCredentialId(Long credentialId){ this.credentialId = credentialId; }
    public void setRequestedBy(String requestedBy){ this.requestedBy = requestedBy; }
    public void setVerificationMethod(String verificationMethod){ this.verificationMethod = verificationMethod; }
    public void setStatus(String status){ this.status = status; }
    public void setVerifiedAt(LocalDateTime verifiedAt){ this.verifiedAt = verifiedAt; }
    public void setResultMessage(String resultMessage){ this.resultMessage = resultMessage; }
}
