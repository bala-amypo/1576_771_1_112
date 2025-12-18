package com.example.demo.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "credential_holder_profiles")
public class CredentialHolderProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String holderId;

    private String fullName;

    @Column(unique = true)
    private String email;

    private String organization;

    private boolean active;

    private LocalDateTime createdAt;

    public CredentialHolderProfile(){}

    public CredentialHolderProfile(String holderId, String fullName,
                                   String email, String organization,
                                   boolean active) {
        this.holderId = holderId;
        this.fullName = fullName;
        this.email = email;
        this.organization = organization;
        this.active = active;
        this.createdAt = LocalDateTime.now();
    }

    public Long getId(){ return id; }
    public String getHolderId(){ return holderId; }
    public String getFullName(){ return fullName; }
    public String getEmail(){ return email; }
    public String getOrganization(){ return organization; }
    public boolean isActive(){ return active; }
    public LocalDateTime getCreatedAt(){ return createdAt; }

    public void setId(Long id){ this.id = id; }
    public void setHolderId(String holderId){ this.holderId = holderId; }
    public void setFullName(String fullName){ this.fullName = fullName; }
    public void setEmail(String email){ this.email = email; }
    public void setOrganization(String organization){ this.organization = organization; }
    public void setActive(boolean active){ this.active = active; }
    public void setCreatedAt(LocalDateTime createdAt){ this.createdAt = createdAt; }
}
