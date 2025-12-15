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
}
