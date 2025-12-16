package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class CredentialHolderProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true)
    private String holderId;
    private String fullName;
    @Column(unique = true)
    private String email;
    private String organization;
    private boolean active;

    public CredentialHolderProfile() {}

    public CredentialHolderProfile(boolean active, String email, String fullName, String holderId, String organization) {
        this.active = active;
        this.email = email;
        this.fullName = fullName;
        this.holderId = holderId;
        this.organization = organization;
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
}
