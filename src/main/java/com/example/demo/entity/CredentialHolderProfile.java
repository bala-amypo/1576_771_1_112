package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "credential_holder_profiles")
public class CredentialHolderProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;

    private String organization;

    private Boolean active;

    public CredentialHolderProfile() {}

    public Long getId() { return id; }
    public String getEmail() { return email; }
    public String getOrganization() { return organization; }
    public Boolean getActive() { return active; }

    public void setId(Long id) { this.id = id; }
    public void setEmail(String email) { this.email = email; }
    public void setOrganization(String organization) { this.organization = organization; }
    public void setActive(Boolean active) { this.active = active; }
}
