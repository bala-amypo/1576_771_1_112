package com.example.demo.entity;

public class VerificationRequest {
    @Id
    private long id;
    private long credentialId;
    private String requestedBy;
    private String verificationMethod;
    private String status;
    

}