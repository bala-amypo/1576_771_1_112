package com.example.demo.entity;

import java.time.LocalDate;

public class CredentialRecord {
    private long id;
    private long holderId;
    private String credentialCode;
    private String title;
    private String issuer;
    private LocalDate issueDate;
    private LocalDate expiryDate;
    private String credentialType;
    private String status;
    private String metadataJson;

    public CredentialRecord(){}

    public CredentialRecord(long holderId, String credentialCode, String title, String issuer, LocalDate issueDate,
            LocalDate expiryDate, String credentialType, String status, String metadataJson) {
        this.holderId = holderId;
        this.credentialCode = credentialCode;
        this.title = title;
        this.issuer = issuer;
        this.issueDate = issueDate;
        this.expiryDate = expiryDate;
        this.credentialType = credentialType;
        this.status = status;
        this.metadataJson = metadataJson;
    }

    public long getId() {
        return id;
    }

    public long getHolderId() {
        return holderId;
    }

    public String getCredentialCode() {
        return credentialCode;
    }

    public String getTitle() {
        return title;
    }

    public String getIssuer() {
        return issuer;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public String getCredentialType() {
        return credentialType;
    }

    public String getStatus() {
        return status;
    }

    public String getMetadataJson() {
        return metadataJson;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setHolderId(long holderId) {
        this.holderId = holderId;
    }

    public void setCredentialCode(String credentialCode) {
        this.credentialCode = credentialCode;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public void setCredentialType(String credentialType) {
        this.credentialType = credentialType;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setMetadataJson(String metadataJson) {
        this.metadataJson = metadataJson;
    }

    
}
