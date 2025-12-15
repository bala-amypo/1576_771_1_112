package com.example.demo.entity;

import java.time.LocalDateTime;

public class CredentialHolderProfile {
    private long id;
    private String holderId;
    private String fullName;
    private String email;
    private String organization;
    private boolean active;
    private LocalDateTime createdAt;

    public CredentialHolderProfile(){}

    public CredentialHolderProfile(String holderId, String fullName, String email, String organization, boolean active,
            LocalDateTime createdAt) {
        this.holderId = holderId;
        this.fullName = fullName;
        this.email = email;
        this.organization = organization;
        this.active = active;
        this.createdAt = createdAt;
    }

    public long getId() {
        return id;
    }

    public String getHolderId() {
        return holderId;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getOrganization() {
        return organization;
    }

    public boolean isActive() {
        return active;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setHolderId(String holderId) {
        this.holderId = holderId;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    
}
